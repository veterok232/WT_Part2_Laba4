package by.bsuir.laba4.repository;

import by.bsuir.laba4.domain.mapper.Mapper;
import by.bsuir.laba4.domain.entity.BaseEntity;
import by.bsuir.laba4.exception.DataSourceException;
import by.bsuir.laba4.repository.queryBuilder.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Abstract repository class
 *
 * @param <T> repository type
 */
public abstract class AbstractRepository<T extends BaseEntity> implements Repository<T> {
    /**
     * Current connection
     */
    private final Connection connection;

    /**
     * Constructor
     *
     * @param connection current connection
     */
    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Execute query
     *
     * @param sql sql query
     * @param mapper mapper
     * @param params parameters
     *
     * @return List<T>
     *
     * @throws DataSourceException
     */
    public List<T> executeQuery(String sql, Mapper<T> mapper, List<Object> params) throws DataSourceException {
        List<T> objects = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            QueryBuilder.prepare(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                T item = mapper.build(resultSet);
                objects.add(item);
            }
        } catch (SQLException ex) {
            throw new DataSourceException(ex.getMessage());
        }
        return objects;
    }

    /**
     * Execute single result query
     *
     * @param query sql query
     * @param mapper mapper
     * @param params parameters
     *
     * @return Optional<T>
     *
     * @throws DataSourceException
     */
    public Optional<T> executeQueryForSingleResult(String query, Mapper<T> mapper, List<Object> params) throws DataSourceException {
        List<T> items = executeQuery(query, mapper, params);

        return items.size() == 1 ? Optional.of(items.get(0)) : Optional.empty();
    }

    /**
     * Save a new data
     *
     * @param item item data
     *
     * @throws DataSourceException
     */
    public void save(T item) throws DataSourceException {
        try {
            String sql;
            if (item.getId() != null) {
                sql = QueryBuilder.makeUpdateQuery(getFields(item), getTableName());
            } else {
                sql = QueryBuilder.makeInsertQuery(getFields(item), getTableName());
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            QueryBuilder.prepare(preparedStatement, getFields(item));

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataSourceException(ex.getMessage());
        }
    }
}

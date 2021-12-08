package by.bsuir.laba4.repository.impl;

import by.bsuir.laba4.domain.mapper.Mapper;
import by.bsuir.laba4.domain.mapper.UserMapper;
import by.bsuir.laba4.domain.entity.User;
import by.bsuir.laba4.exception.DataSourceException;
import by.bsuir.laba4.repository.AbstractRepository;
import by.bsuir.laba4.query.CustomQuery;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * User repository
 */
public class UserRepository extends AbstractRepository<User> {
    /**
     * Table name
     */
    private static final String TABLE_NAME = "user";

    /**
     * User id
     */
    private static final String ID = "id";

    /**
     * User name
     */
    private static final String USERNAME = "username";

    /**
     * Password
     */
    private static final String PASSWORD = "password";

    /**
     * User role
     */
    private static final String ROLE = "role";

    /**
     * Select query
     */
    private static final String SELECT_QUERY = "SELECT * FROM user ";

    /**
     * Constructor
     *
     * @param connection current connection
     */
    public UserRepository(Connection connection) {
        super(connection);
    }

    /**
     * Get table fields
     *
     * @param item item
     *
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getFields(User item) {
        Map<String, Object> values = new LinkedHashMap<>();

        values.put(USERNAME, item.getUsername());
        values.put(PASSWORD, item.getPassword());
        values.put(ROLE, item.getRole());
        values.put(ID, item.getId());

        return values;
    }

    /**
     * Get table name
     *
     * @return String
     */
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    /**
     * User repository query
     *
     * @param customQuery custom query
     *
     * @return Optional<User>
     *
     * @throws DataSourceException
     */
    @Override
    public Optional<User> query(CustomQuery customQuery) throws DataSourceException {
        String query = SELECT_QUERY + customQuery.toSql();
        Mapper<User> mapper = new UserMapper();
        List<Object> params = customQuery.getParameters();

        return executeQueryForSingleResult(query, mapper, params);
    }

    /**
     * User repository query all
     *
     * @param customQuery custom query
     *
     * @return List<User>
     *
     * @throws DataSourceException
     */
    @Override
    public List<User> queryAll(CustomQuery customQuery) throws DataSourceException {
        String query = SELECT_QUERY + customQuery.toSql();
        Mapper<User> mapper = new UserMapper();
        List<Object> params = customQuery.getParameters();

        return executeQuery(query, mapper, params);
    }

}

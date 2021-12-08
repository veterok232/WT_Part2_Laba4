package by.bsuir.laba4.repository;

import by.bsuir.laba4.exception.DataSourceException;
import by.bsuir.laba4.query.CustomQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repository interface
 *
 * @param <T> repository type
 */
public interface Repository<T> {
    /**
     * Repository query
     *
     * @param customQuery custom query
     *
     * @return Optional<T>
     *
     * @throws DataSourceException
     */
    Optional<T> query(CustomQuery customQuery) throws DataSourceException;

    /**
     * Repository query all
     *
     * @param customQuery custom query
     *
     * @return List<T>
     *
     * @throws DataSourceException
     */
    List<T> queryAll(CustomQuery customQuery) throws DataSourceException;

    /**
     * Save a new data
     *
     * @param item item data
     *
     * @throws DataSourceException
     */
    void save(T item) throws DataSourceException;

    /**
     * Get table name
     *
     * @return String
     */
    String getTableName();

    /**
     * Get table fields
     *
     * @param item item
     *
     * @return Map<String, Object>
     */
    Map<String, Object> getFields(T item);
}

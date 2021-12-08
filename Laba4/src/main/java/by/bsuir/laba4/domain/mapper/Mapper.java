package by.bsuir.laba4.domain.mapper;

import by.bsuir.laba4.exception.DataSourceException;
import java.sql.ResultSet;

/**
 * Mapper interface
 *
 * @param <T> mapper type
 */
public interface Mapper<T> {
    /**
     * Build entity instance
     *
     * @param resultSet result
     *
     * @return T
     *
     * @throws DataSourceException
     */
    T build(ResultSet resultSet) throws DataSourceException;
}

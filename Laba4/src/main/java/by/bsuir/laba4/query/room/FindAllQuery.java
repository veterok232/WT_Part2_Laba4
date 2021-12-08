package by.bsuir.laba4.query.room;

import by.bsuir.laba4.query.CustomQuery;
import java.util.Collections;
import java.util.List;

/**
 * Find all rooms query
 */
public class FindAllQuery implements CustomQuery {
    /**
     * Get sql part
     *
     * @return String
     */
    @Override
    public String toSql() {
        return "";
    }

    /**
     * Get parameters
     *
     * @return List<Object>
     */
    @Override
    public List<Object> getParameters() {
        return Collections.emptyList();
    }
}

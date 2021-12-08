package by.bsuir.laba4.query.room;

import by.bsuir.laba4.query.CustomQuery;
import java.util.Collections;
import java.util.List;

/**
 * Find free rooms query
 */
public class FindFreeQuery implements CustomQuery {
    /**
     * Get sql part
     *
     * @return String
     */
    @Override
    public String toSql() {
        return "WHERE occupied = 'false'";
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

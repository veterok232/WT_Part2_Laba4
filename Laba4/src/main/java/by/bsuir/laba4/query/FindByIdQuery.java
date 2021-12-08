package by.bsuir.laba4.query;

import java.util.Collections;
import java.util.List;

/**
 * Find by id query class
 */
public class FindByIdQuery implements CustomQuery {
    /**
     * Id
     */
    private final Integer id;

    /**
     * Constructor
     *
     * @param id id
     */
    public FindByIdQuery(Integer id) {
        this.id = id;
    }

    /**
     * Get sql part
     *
     * @return String
     */
    @Override
    public String toSql() {
        return "where id = ?";
    }

    /**
     * Get parameters
     *
     * @return List<Object>
     */
    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(id);
    }
}

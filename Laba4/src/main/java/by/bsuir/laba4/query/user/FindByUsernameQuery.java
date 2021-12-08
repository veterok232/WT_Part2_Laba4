package by.bsuir.laba4.query.user;

import by.bsuir.laba4.query.CustomQuery;
import java.util.Collections;
import java.util.List;

/**
 * Find users by username query
 */
public class FindByUsernameQuery implements CustomQuery {
    /**
     * User name
     */
    private final String username;

    /**
     * Constructor
     *
     * @param username user name
     */
    public FindByUsernameQuery(String username) {
        this.username = username;
    }

    /**
     * Get sql part
     *
     * @return String
     */
    @Override
    public String toSql() {
        return "WHERE username=?";
    }

    /**
     * Get parameters
     *
     * @return List<Object>
     */
    @Override
    public List<Object> getParameters() {
        return Collections.singletonList(username);
    }
}

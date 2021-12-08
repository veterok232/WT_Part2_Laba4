package by.bsuir.laba4.query.user;

import by.bsuir.laba4.query.CustomQuery;
import java.util.Arrays;
import java.util.List;

/**
 * Find users by username and password query
 */
public class FindByUsernameAndPasswordQuery implements CustomQuery {
    /**
     * User name
     */
    private final String username;

    /**
     * Password
     */
    private final String password;

    /**
     * Constructor
     *
     * @param username user name
     * @param password password
     */
    public FindByUsernameAndPasswordQuery(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get sql part
     *
     * @return String
     */
    @Override
    public String toSql() {
        return "where username = ? AND password = ?";
    }

    /**
     * Get parameters
     *
     * @return List<Object>
     */
    @Override
    public List<Object> getParameters() {
        return Arrays.asList(username, password);
    }
}

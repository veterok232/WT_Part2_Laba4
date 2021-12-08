package by.bsuir.laba4.domain.mapper;

import by.bsuir.laba4.domain.entity.Role;
import by.bsuir.laba4.domain.entity.User;
import by.bsuir.laba4.exception.DataSourceException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User mapper class
 */
public class UserMapper implements Mapper<User> {
    /**
     * Id label
     */
    private static final String ID = "id";

    /**
     * User name label
     */
    private static final String USERNAME = "username";

    /**
     * User password
     */
    private static final String PASSWORD = "password";

    /**
     * User role
     */
    private static final String ROLE = "role";

    /**
     * Build user instance
     *
     * @param resultSet result
     *
     * @return User
     *
     * @throws DataSourceException
     */
    @Override
    public User build(ResultSet resultSet) throws DataSourceException {
        try {
            Integer id = resultSet.getInt(ID);
            String login = resultSet.getString(USERNAME);
            String password = resultSet.getString(PASSWORD);
            Role role = Role.valueOf(resultSet.getString(ROLE).toUpperCase());

            return new User(id, login, password, role);
        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }
}

package by.bsuir.laba4.service;

import by.bsuir.laba4.domain.entity.User;
import by.bsuir.laba4.exception.DataSourceException;
import by.bsuir.laba4.exception.CustomException;
import by.bsuir.laba4.repository.creator.RepositoryCreator;
import by.bsuir.laba4.repository.impl.UserRepository;
import by.bsuir.laba4.query.user.FindByUsernameQuery;
import by.bsuir.laba4.query.user.FindByUsernameAndPasswordQuery;
import java.util.Optional;

/**
 * User service class
 */
public class UserService {
    /**
     * Find user by username and password
     *
     * @param username user name
     * @param password password
     *
     * @return Optional<User>
     *
     * @throws CustomException
     */
    public Optional<User> findByUsernameAndPassword(String username, String password) throws CustomException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();

            return userRepository.query(new FindByUsernameAndPasswordQuery(username, password));
        } catch (DataSourceException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * Find user by username
     * @param username user name
     *
     * @return Optional<User>
     *
     * @throws CustomException
     */
    public Optional<User> findByUsername(String username) throws CustomException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();

            return userRepository.query(new FindByUsernameQuery(username));

        } catch (DataSourceException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * Register a new user
     *
     * @param id user id
     * @param username user name
     * @param password password
     *
     * @throws CustomException
     */
    public void signUp(Integer id, String username, String password) throws CustomException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            User user = new User(id, username, password);
            userRepository.save(user);
        } catch (DataSourceException ex) {
            throw new CustomException(ex.getMessage());
        }
    }
}

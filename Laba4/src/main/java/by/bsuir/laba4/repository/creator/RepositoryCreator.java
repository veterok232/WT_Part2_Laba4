package by.bsuir.laba4.repository.creator;

import by.bsuir.laba4.datasource.ConnectionPool;
import by.bsuir.laba4.repository.impl.RoomRepository;
import by.bsuir.laba4.repository.impl.UserRepository;
import java.sql.Connection;

/**
 * Repository creator class
 */
public class RepositoryCreator implements AutoCloseable {
    /**
     * Connection pull
     */
    private final ConnectionPool connectionPool;

    /**
     * Current connection
     */
    private final Connection connection;

    /**
     * Constructor
     */
    public RepositoryCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    /**
     * User repository getter
     *
     * @return UserRepository
     */
    public UserRepository getUserRepository() {
        return new UserRepository(connection);
    }

    /**
     * Room repository getter
     *
     * @return RoomRepository
     */
    public RoomRepository getRoomRepository() {
        return new RoomRepository(connection);
    }

    /**
     * Close current connection
     */
    @Override
    public void close() {
        connectionPool.returnConnection(connection);
    }
}

package by.bsuir.laba4.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection pool class
 */
public class ConnectionPool {
    /**
     * Lock object
     */
    private static final ReentrantLock lock = new ReentrantLock();

    /**
     * Connection lock object
     */
    private static final ReentrantLock connectionLock = new ReentrantLock();

    /**
     * Return lock object
     */
    private static final ReentrantLock returnLock = new ReentrantLock();

    /**
     * Initialized
     */
    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    /**
     * Connection pool instance
     */
    private static ConnectionPool instance;

    /**
     * Connection builder instance
     */
    private final ConnectionBuilder connectionBuilder = new ConnectionBuilder();

    /**
     * Connections
     */
    private Deque<Connection> connections;

    /**
     * Semaphore
     */
    private Semaphore semaphore;

    /**
     * Connections size
     */
    private int connectionSize;

    /**
     * Constructor
     */
    private ConnectionPool() {
        readConnectionSizeFromProperties();
        initConnections();
        createConnections();
    }

    /**
     * Get connection pool instance
     *
     * @return ConnectionPool
     */
    public static ConnectionPool getInstance() {
        if (!initialized.get()) {
            try {
                lock.lock();
                if (!initialized.get()) {
                    instance = new ConnectionPool();
                    initialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Init a new connection
     */
    private void initConnections() {
        connections = new ArrayDeque<>();
        semaphore = new Semaphore(connectionSize);
    }

    /**
     * Create a new connection
     */
    private void createConnections() {
        for (int i = 0; i < connectionSize; i++) {
            Connection connection = connectionBuilder.createConnection();
            connections.push(connection);
        }
        if (connections.isEmpty()) {
            throw new IllegalArgumentException("Connections are not created!");
        }
    }

    /**
     * Connection size getter
     */
    private void readConnectionSizeFromProperties() {
        try {
            Class<? extends ConnectionPool> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");

            Properties property = new Properties();
            property.load(inputStream);

            connectionSize = Integer.parseInt(property.getProperty("db.connectionSize"));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found" + e.getMessage(), e);
        }
    }

    /**
     * Connection getter
     *
     * @return Connection
     */
    public Connection getConnection() {
        try {
            connectionLock.lock();
            semaphore.acquire();

            return connections.pop();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException();
        } finally {
            connectionLock.unlock();
        }
    }

    /**
     * Return a new connection
     *
     * @param connection current connection
     */
    public void returnConnection(Connection connection) {
        try {
            returnLock.lock();
            connections.push(connection);
            semaphore.release();
        } finally {
            returnLock.unlock();
        }
    }
}

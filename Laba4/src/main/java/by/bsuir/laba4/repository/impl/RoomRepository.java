package by.bsuir.laba4.repository.impl;

import by.bsuir.laba4.domain.mapper.HotelRoomMapper;
import by.bsuir.laba4.domain.entity.HotelRoom;
import by.bsuir.laba4.exception.DataSourceException;
import by.bsuir.laba4.repository.AbstractRepository;
import by.bsuir.laba4.query.CustomQuery;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Room repository class
 */
public class RoomRepository extends AbstractRepository<HotelRoom> {
    /**
     * Table name
     */
    private static final String TABLE_NAME = " `room` ";

    /**
     * Room id
     */
    private static final String ID = "id";

    /**
     * Room number
     */
    private static final String ROOM_NUMBER = "room_number";

    /**
     * Is room occupied
     */
    private static final String OCCUPIED = "occupied";

    /**
     * Select query
     */
    private static final String SELECT_QUERY = "SELECT * FROM `room` ";

    /**
     * Constructor
     *
     * @param connection current connection
     */
    public RoomRepository(Connection connection) {
        super(connection);
    }

    /**
     * Get table fields
     *
     * @param item item
     *
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getFields(HotelRoom item) {
        Map<String, Object> values = new LinkedHashMap<>();

        values.put(ROOM_NUMBER, item.getRoomNumber());
        values.put(OCCUPIED, item.getOccupied());
        values.put(ID, item.getId());

        return values;
    }

    /**
     * Get table name
     *
     * @return String
     */
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    /**
     * Room repository query
     *
     * @param customQuery custom query
     *
     * @return Optional<HotelRoom>
     *
     * @throws DataSourceException
     */
    @Override
    public Optional<HotelRoom> query(CustomQuery customQuery) throws DataSourceException {
        String query = SELECT_QUERY + customQuery.toSql();
        List<Object> params = customQuery.getParameters();

        return executeQueryForSingleResult(query, new HotelRoomMapper(), params);
    }

    /**
     * Room repository query all
     *
     * @param customQuery custom query
     *
     * @return List<HotelRoom>
     *
     * @throws DataSourceException
     */
    @Override
    public List<HotelRoom> queryAll(CustomQuery customQuery) throws DataSourceException {
        String query = SELECT_QUERY + customQuery.toSql();
        List<Object> params = customQuery.getParameters();

        return executeQuery(query, new HotelRoomMapper(), params);
    }
}

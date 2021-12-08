package by.bsuir.laba4.domain.mapper;

import by.bsuir.laba4.domain.entity.HotelRoom;
import by.bsuir.laba4.exception.DataSourceException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hotel room mapper class
 */
public class HotelRoomMapper implements Mapper<HotelRoom> {
    /**
     * Id label
     */
    private static final String ID = "id";

    /**
     * Room number label
     */
    private static final String ROOM_NUMBER = "room_number";

    /**
     * Is occupied label
     */
    private static final String OCCUPIED = "occupied";

    /**
     * Build hotel room instance
     *
     * @param resultSet result
     *
     * @return HotelRoom
     *
     * @throws DataSourceException
     */
    @Override
    public HotelRoom build(ResultSet resultSet) throws DataSourceException {
        try {
            Integer id = resultSet.getInt(ID);
            String roomNumber = resultSet.getString(ROOM_NUMBER);
            Boolean occupied = resultSet.getBoolean(OCCUPIED);

            return new HotelRoom(id, roomNumber, occupied);
        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }
}

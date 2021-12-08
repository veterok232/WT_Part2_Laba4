package by.bsuir.laba4.service;

import by.bsuir.laba4.domain.entity.HotelRoom;
import by.bsuir.laba4.exception.DataSourceException;
import by.bsuir.laba4.exception.CustomException;
import by.bsuir.laba4.repository.creator.RepositoryCreator;
import by.bsuir.laba4.repository.impl.RoomRepository;
import by.bsuir.laba4.query.FindByIdQuery;
import by.bsuir.laba4.query.room.FindAllQuery;
import by.bsuir.laba4.query.room.FindFreeQuery;
import java.util.List;
import java.util.Optional;

/**
 * Hotel room service class
 */
public class HotelRoomService {
    /**
     * Find all rooms
     *
     * @return List<HotelRoom>
     *
     * @throws CustomException
     */
    public List<HotelRoom> findAll() throws CustomException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RoomRepository roomRepository = repositoryCreator.getRoomRepository();

            return roomRepository.queryAll(new FindAllQuery());
        } catch (DataSourceException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * Find all free rooms
     *
     * @return List<HotelRoom>
     *
     * @throws CustomException
     */
    public List<HotelRoom> findFree() throws CustomException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RoomRepository roomRepository = repositoryCreator.getRoomRepository();

            return roomRepository.queryAll(new FindFreeQuery());
        } catch (DataSourceException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * Save a new room data
     *
     * @param id room id
     * @param roomNumber room number
     * @param occupied is room occupied
     *
     * @throws CustomException
     */
    public void save(Integer id, String roomNumber, Boolean occupied) throws CustomException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RoomRepository roomRepository = repositoryCreator.getRoomRepository();
            HotelRoom hotelRoom = new HotelRoom(id, roomNumber, occupied);
            roomRepository.save(hotelRoom);
        } catch (DataSourceException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    /**
     * Change room status
     *
     * @param id room id
     * @param occupied is room occupied
     *
     * @throws CustomException
     */
    public void changeStatus(Integer id, Boolean occupied) throws CustomException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RoomRepository roomRepository = repositoryCreator.getRoomRepository();
            Optional<HotelRoom> room = roomRepository.query(new FindByIdQuery(id));

            if (room.isPresent()) {
                room.get().setOccupied(occupied);
                roomRepository.save(room.get());
            } else {
                throw new CustomException(String.format("Room with id=%s not found.", id));
            }
        } catch (DataSourceException ex) {
            throw new CustomException(ex.getMessage());
        }
    }
}

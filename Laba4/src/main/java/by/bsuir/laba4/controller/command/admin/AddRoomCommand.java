package by.bsuir.laba4.controller.command.admin;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import by.bsuir.laba4.domain.entity.HotelRoom;
import by.bsuir.laba4.exception.CustomException;
import by.bsuir.laba4.service.HotelRoomService;
import by.bsuir.laba4.validation.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Add room command class
 */
public class AddRoomCommand implements Command {
    /**
     * Page path
     */
    private static final String MAIN_PAGE = "controller?command=showRooms";

    /**
     * Room number label
     */
    private static final String ROOM_NUMBER = "roomNumber";

    /**
     * Room list label
     */
    private static final String ROOM_LIST = "roomList";

    /**
     * Adding room label
     */
    private static final String ADDING_ROOM = "added";

    /**
     * Message label
     */
    private static final String MESSAGE = "&message=";

    /**
     * Invalid room label
     */
    private static final String ERROR_MESSAGE = "invalidRoom";

    /**
     * Execute command
     *
     * @param request request
     * @param response response
     *
     * @return CommandResult
     *
     * @throws CustomException
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        String roomNumber = request.getParameter(ROOM_NUMBER);
        Validation validation = new Validation();
        Map<String, String> values = new HashMap<>();
        values.put(ROOM_NUMBER, roomNumber);

        if (!validation.isValid(values)) {
            return CommandResult.redirect(MAIN_PAGE + MESSAGE + ERROR_MESSAGE);
        }

        HotelRoomService roomService = new HotelRoomService();
        roomService.save(null, roomNumber, false);

        List<HotelRoom> hotelRoomList = roomService.findAll();
        request.setAttribute(ROOM_LIST, hotelRoomList);

        return CommandResult.redirect(MAIN_PAGE + MESSAGE + ADDING_ROOM);
    }
}

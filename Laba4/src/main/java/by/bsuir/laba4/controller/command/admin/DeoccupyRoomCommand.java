package by.bsuir.laba4.controller.command.admin;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import by.bsuir.laba4.domain.entity.HotelRoom;
import by.bsuir.laba4.exception.CustomException;
import by.bsuir.laba4.service.HotelRoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Deocuppy room command class
 */
public class DeoccupyRoomCommand implements Command {
    /**
     * Page path
     */
    private static final String MAIN_PAGE = "controller?command=showRooms";

    /**
     * Room list label
     */
    private static final String ROOM_LIST = "roomList";

    /**
     * Room id label
     */
    private static final String ROOM_ID = "roomId";

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
        String roomId = request.getParameter(ROOM_ID);

        HotelRoomService roomService = new HotelRoomService();
        roomService.changeStatus(Integer.valueOf(roomId), false);

        List<HotelRoom> hotelRoomList = roomService.findAll();
        request.setAttribute(ROOM_LIST, hotelRoomList);

        return CommandResult.redirect(MAIN_PAGE);
    }
}

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
 * Show rooms command class
 */
public class ShowRoomsCommand implements Command {
    /**
     * Page path
     */
    private static final String ROOMS_PAGE = "/WEB-INF/pages/admin/rooms.jsp";

    /**
     * Room list label
     */
    private static final String ROOM_LIST = "roomList";

    /**
     * Message label
     */
    private static final String MESSAGE = "message";

    /**
     * Notify message label
     */
    private static final String NOTIFY_MESSAGE = "notifyMessage";

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
        HotelRoomService roomService = new HotelRoomService();
        List<HotelRoom> fullHotelRoomList = roomService.findAll();
        request.setAttribute(ROOM_LIST, fullHotelRoomList);

        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }

        return CommandResult.forward(ROOMS_PAGE);
    }
}

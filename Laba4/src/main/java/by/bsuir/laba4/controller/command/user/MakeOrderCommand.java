package by.bsuir.laba4.controller.command.user;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import by.bsuir.laba4.exception.CustomException;
import by.bsuir.laba4.service.HotelRoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main order command
 */
public class MakeOrderCommand implements Command {
    /**
     * Page path
     */
    private static final String MAIN_PAGE = "controller?command=mainPage";

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
        roomService.changeStatus(Integer.valueOf(roomId), true);

        return CommandResult.redirect(MAIN_PAGE);
    }
}

package by.bsuir.laba4.controller.command.user;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import by.bsuir.laba4.domain.entity.HotelRoom;
import by.bsuir.laba4.exception.CustomException;
import by.bsuir.laba4.service.HotelRoomService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Main page command
 */
public class MainPageCommand implements Command {
    /**
     * Page path
     */
    private static final String MAIN_PAGE = "/WEB-INF/pages/makeOrder.jsp";

    /**
     * Room list label
     */
    private static final String ROOM_LIST = "roomList";

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
        List<HotelRoom> freeHotelRoomList = roomService.findFree();
        request.setAttribute(ROOM_LIST, freeHotelRoomList);

        return CommandResult.forward(MAIN_PAGE);
    }
}

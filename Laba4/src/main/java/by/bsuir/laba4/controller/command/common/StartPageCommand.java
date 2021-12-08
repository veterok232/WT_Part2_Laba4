package by.bsuir.laba4.controller.command.common;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import by.bsuir.laba4.exception.CustomException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Start page command class
 */
public class StartPageCommand implements Command {
    /**
     * Page path
     */
    private static final String LOGIN_PAGE = "/index.jsp";

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
        return CommandResult.forward(LOGIN_PAGE);
    }
}

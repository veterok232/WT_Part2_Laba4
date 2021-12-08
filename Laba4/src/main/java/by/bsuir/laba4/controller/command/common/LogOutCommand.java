package by.bsuir.laba4.controller.command.common;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout command class
 */
public class LogOutCommand implements Command {
    /**
     * Page path
     */
    private static final String LOGIN_PAGE = "/WEB-INF/pages/login.jsp";

    /**
     * User id label
     */
    private static final String ID = "id";

    /**
     * User name label
     */
    private static final String USERNAME = "username";

    /**
     * User role label
     */
    private static final String ROLE = "role";

    /**
     * Execute command
     *
     * @param request request
     * @param response response
     *
     * @return CommandResult
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(ID);
        session.removeAttribute(USERNAME);
        session.removeAttribute(ROLE);

        return CommandResult.forward(LOGIN_PAGE);
    }
}

package by.bsuir.laba4.controller.command.common;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import by.bsuir.laba4.domain.entity.Role;
import by.bsuir.laba4.domain.entity.User;
import by.bsuir.laba4.exception.CustomException;
import by.bsuir.laba4.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Login command class
 */
public class LoginCommand implements Command {
    /**
     * Page path
     */
    private static final String MAIN_PAGE = "controller?command=mainPage";

    /**
     * Page path
     */
    private static final String ADMIN_PAGE = "controller?command=showRooms";

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
     * Password label
     */
    private static final String PASSWORD = "password";

    /**
     * Message label
     */
    private static final String ERROR_MESSAGE = "errorMessage";

    /**
     * Parameter label
     */
    private static final String WRONG_PARAMETER = "Wrong login or password";

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
        HttpSession session = request.getSession();
        UserService service = new UserService();
        String login = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        Optional<User> optionalUser = service.findByUsernameAndPassword(login, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Role role = user.getRole();

            session.setAttribute(ID, user.getId());
            session.setAttribute(USERNAME, user.getUsername());
            session.setAttribute(ROLE, role);

            return Role.ADMIN.equals(role) ? CommandResult.redirect(ADMIN_PAGE) : CommandResult.redirect(MAIN_PAGE);
        } else {
            request.setAttribute(ERROR_MESSAGE, WRONG_PARAMETER);

            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}

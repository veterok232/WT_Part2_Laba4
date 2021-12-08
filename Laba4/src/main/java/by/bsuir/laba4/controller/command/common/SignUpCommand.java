package by.bsuir.laba4.controller.command.common;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import by.bsuir.laba4.domain.entity.User;
import by.bsuir.laba4.exception.CustomException;
import by.bsuir.laba4.service.UserService;
import by.bsuir.laba4.validation.Validation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Register command class
 */
public class SignUpCommand implements Command {
    /**
     * Page path
     */
    private static final String START_PAGE = "controller?command=startLogin";

    /**
     * Page path
     */
    private static final String LOGIN_PAGE = "/WEB-INF/pages/login.jsp";

    /**
     * Error label
     */
    private static final String SIGN_UP_ERROR = "signUpError";

    /**
     * User name label
     */
    private static final String USERNAME = "username";

    /**
     * Password label
     */
    private static final String PASSWORD = "password";

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
        String login = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        Map<String, String> signUpData = new HashMap<>();
        signUpData.put(USERNAME, login);
        Validation validation = new Validation();

        if (!validation.isValid(signUpData)) {
            String errorName = validation.getInvalidData();
            request.setAttribute(SIGN_UP_ERROR, errorName);

            return CommandResult.forward(LOGIN_PAGE);
        }

        UserService userService = new UserService();
        Optional<User> optionalUser = userService.findByUsername(login);
        if (optionalUser.isPresent()) {
            request.setAttribute(SIGN_UP_ERROR, USERNAME);

            return CommandResult.forward(LOGIN_PAGE);
        }
        userService.signUp(null, login, password);

        return CommandResult.redirect(START_PAGE);
    }
}

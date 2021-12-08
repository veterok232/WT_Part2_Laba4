package by.bsuir.laba4.controller.command.common;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Change language command class
 */
public class ChangeLanguageCommand implements Command {
    /**
     * Page path
     */
    private static final String START_PAGE = "startPage";

    /**
     * Language label
     */
    private static final String LANGUAGE = "language";

    /**
     * Page path
     */
    private static final String REDIRECT_COMMAND = "controller?command=";

    /**
     * Language label
     */
    private static final String LANG = "lang";

    /**
     * Command index label
     */
    private static final Integer COMMAND_INDEX = 46;

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
        String language = request.getParameter(LANG);
        String query = request.getQueryString();
        session.setAttribute(LANGUAGE, language);
        if (query.length() > COMMAND_INDEX) {
            String page = query.substring(COMMAND_INDEX);

            return CommandResult.redirect(REDIRECT_COMMAND + page);
        } else {
            return CommandResult.redirect(REDIRECT_COMMAND + START_PAGE);
        }
    }
}

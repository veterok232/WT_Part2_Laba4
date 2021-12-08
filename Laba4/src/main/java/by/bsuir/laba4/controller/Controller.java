package by.bsuir.laba4.controller;

import by.bsuir.laba4.controller.command.Command;
import by.bsuir.laba4.controller.command.CommandFactory;
import by.bsuir.laba4.controller.command.CommandResult;
import by.bsuir.laba4.exception.CustomException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main controller
 */
public class Controller extends HttpServlet {
    /**
     * Command label
     */
    private static final String COMMAND = "command";

    /**
     * Error page path
     */
    private static final String ERROR_PAGE = "/WEB-INF/pages/error/Error500.jsp";

    /**
     * Get query
     *
     * @param req request
     * @param resp response
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Post query
     *
     * @param req request
     * @param resp response
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Process query
     *
     * @param req request
     * @param resp response
     *
     * @throws ServletException
     * @throws IOException
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter(COMMAND);
        Command command = CommandFactory.getInstance().getCommand(parameter);

        try {
            CommandResult commandResult = command.execute(req, resp);
            String page = commandResult.getPage();

            if (commandResult.isRedirect()) {
                resp.sendRedirect(page);
            } else {
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);
                requestDispatcher.forward(req, resp);
            }
        } catch (CustomException e) {
            CommandResult.forward(ERROR_PAGE);
        }
    }
}

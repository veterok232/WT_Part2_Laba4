package by.bsuir.laba4.controller.command;

import by.bsuir.laba4.exception.CustomException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command interface
 */
public interface Command {
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
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CustomException;
}

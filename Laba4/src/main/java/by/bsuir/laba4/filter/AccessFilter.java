package by.bsuir.laba4.filter;

import by.bsuir.laba4.domain.entity.Role;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Access filter class
 */
public class AccessFilter implements Filter {
    /**
     * Show rooms label
     */
    private static final String SHOW_ROOMS = "showRooms";

    /**
     * Main page label
     */
    private static final String MAIN_PAGE = "mainPage";

    /**
     * Make order label
     */
    private static final String MAKE_ORDER = "makeOrder";

    /**
     * Add room label
     */
    private static final String ADD_ROOM = "addRoom";

    /**
     * Deoccupy room label
     */
    private static final String DEOCCUPY_ROOM = "deoccupyRoom";

    /**
     * User role label
     */
    private static final String ROLE = "role";

    /**
     * Command label
     */
    private static final String COMMAND = "command";

    /**
     * Error
     */
    private static final Integer ERROR_NUMBER = 403;

    /**
     * Init method
     *
     * @param filterConfig filter config
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Do filter method
     *
     * @param servletRequest request
     * @param servletResponse response
     * @param filterChain filter chain
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String parameter = servletRequest.getParameter(COMMAND);
        if (parameter != null) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            Role role = (Role) session.getAttribute(ROLE);

            if (parameter.equals(SHOW_ROOMS) || parameter.equals(ADD_ROOM) || parameter.equals(DEOCCUPY_ROOM)) {
                if (role.equals(Role.USER)) {
                    ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                    return;
                }
            } else if (parameter.equals(MAIN_PAGE) || parameter.equals(MAKE_ORDER)) {
                if (role.equals(Role.ADMIN)) {
                    ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Destroy method
     */
    @Override
    public void destroy() {
    }
}

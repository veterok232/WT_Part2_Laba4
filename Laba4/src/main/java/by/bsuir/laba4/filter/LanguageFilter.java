package by.bsuir.laba4.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Language filter class
 */
public class LanguageFilter implements Filter {
    /**
     * Language
     */
    private static final String EN = "EN";

    /**
     * Label
     */
    private static final String LANGUAGE = "language";

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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);
        if (language == null) {
            language = EN;
            session.setAttribute(LANGUAGE, language);
        }

        session.setAttribute(LANGUAGE, language);
        filterChain.doFilter(request, response);
    }

    /**
     * Destroy method
     */
    @Override
    public void destroy() {
    }
}

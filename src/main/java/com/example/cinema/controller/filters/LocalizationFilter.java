package com.example.cinema.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The filter is responsible for setting empty localization and language
 *
 */
@WebFilter("/*")
public class LocalizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String locale = (String) session.getAttribute("locale");
        String language = (String) session.getAttribute("language");
        if (language == null) {
            session.setAttribute("language", "en");
            session.setAttribute("languageButtonLabel", "RU");
        }
        if (locale == null) {
            session.setAttribute("locale", "en");
        }
        String bundle = (String) session.getAttribute("bundle");
        if (bundle == null) {
            session.setAttribute("bundle", "language");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

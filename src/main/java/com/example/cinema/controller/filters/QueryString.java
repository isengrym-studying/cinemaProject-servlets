package com.example.cinema.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * The filter is responsible for getting query of page
 *
 */
@WebFilter("/*")
public class QueryString implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String queryString = Optional.ofNullable(request.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");


        if (!queryString.equals("command=changelanguage") && !queryString.equals("login")) request.getSession().setAttribute("pageQuery", queryString);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

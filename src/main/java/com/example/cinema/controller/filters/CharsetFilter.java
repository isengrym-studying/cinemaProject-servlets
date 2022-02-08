package com.example.cinema.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * The filter is responsible for changing encoding to UTF-8
 *
 */
@WebFilter("/*")
public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }


}

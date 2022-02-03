package com.example.cinema.controller.comand;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Interface for all Command implementations, that are used for getting
 * and sending data to View-layer. Part of command pattern.
 *
 */
public interface ActionCommand {
    String execute(HttpServletRequest req);

    /**
     * Adding pageQuery attribute to session scope
     * pageQuery attribute is being used for building the path to the page from which the command was called
     *
     */
    static void pageAdress(HttpServletRequest req) {
        String queryString = Optional.ofNullable(req.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");

        req.getSession().setAttribute("pageQuery", queryString);
    }
}

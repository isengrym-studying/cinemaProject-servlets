package com.example.cinema.controller.comand.common;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;


/**
 * The command that is responsible for filling main page with content
 *
 */
public class FillMainPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        page = ConfigurationManager.getProperty("path.page.main");
        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}

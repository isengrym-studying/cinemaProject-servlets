package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * The command that is responsible for getting full users information,
 * including registration data and tickets
 *
 */
public class ProfileCommand implements  ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        ActionCommand.pageAdress(req);
        String page = null;

        User user = (User)req.getSession().getAttribute("user");
        req.setAttribute("userName", user.getName());
        req.setAttribute("userSurname", user.getSurname());
        req.setAttribute("userEmail", user.getEmail());
        page = ConfigurationManager.getProperty("path.page.profile");
        return page;
    }
}

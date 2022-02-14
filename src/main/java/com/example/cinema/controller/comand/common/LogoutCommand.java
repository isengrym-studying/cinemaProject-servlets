package com.example.cinema.controller.comand.common;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The command that is responsible for invalidating session (logging out user)
 *
 */
public class LogoutCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.index");
        req.getSession().removeAttribute("user");
        log.info("User with email has logged out");
        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}

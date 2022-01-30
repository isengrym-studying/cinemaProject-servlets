package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.index");
        req.getSession().invalidate();
        log.info("User has logged out");
        return page;
    }
}

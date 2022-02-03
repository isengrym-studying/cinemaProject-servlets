package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Simple empty command
 *
 */
public class EmptyCommand implements ActionCommand{
    private static Logger log = Logger.getLogger(EmptyCommand.class);
    @Override
    public String execute(HttpServletRequest req) {
        log.warn("Empty command was given");
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}

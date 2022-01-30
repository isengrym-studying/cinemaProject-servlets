package com.example.cinema.controller.comand;

import com.example.cinema.controller.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ActionFactory {
    private static Logger log = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest req) {
        ActionCommand current = new EmptyCommand();
        String action = req.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            log.warn("Such command doesn't exist (" + action +")");
            req.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}

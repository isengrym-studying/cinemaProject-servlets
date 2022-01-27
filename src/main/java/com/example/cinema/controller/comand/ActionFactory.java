package com.example.cinema.controller.comand;

import com.example.cinema.controller.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest req) {
        ActionCommand current = new EmptyCommand();
        String action = req.getParameter("command");
        System.out.println(action);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            req.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}

package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class SetLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        HttpSession session = req.getSession();
        if (req.getLocale().getLanguage() != "ru") {
            session.setAttribute("language", "en");
            session.setAttribute("languageButtonLabel", "RU");
        }
        else {
            session.setAttribute("language","ru");
            session.setAttribute("languageButtonLabel", "EN");
        }
        return ConfigurationManager.getProperty("path.page.main");
    }
}

package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import java.util.Locale;


public class ChangeLanguageCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        HttpSession session = req.getSession();
        String language = (String) session.getAttribute("language");

        if (language.equals("ru")) {
            session.setAttribute("language", "en");
            session.setAttribute("languageButtonLabel", "RU");
        }
        else {
            session.setAttribute("language", "ru");
            session.setAttribute("languageButtonLabel", "EN");
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}

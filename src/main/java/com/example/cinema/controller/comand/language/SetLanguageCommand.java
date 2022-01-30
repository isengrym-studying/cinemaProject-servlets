package com.example.cinema.controller.comand.language;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetLanguageCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(SetLanguageCommand.class);
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

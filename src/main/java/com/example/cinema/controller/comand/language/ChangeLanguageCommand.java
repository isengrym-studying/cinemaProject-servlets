package com.example.cinema.controller.comand.language;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(ChangeLanguageCommand.class);
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        HttpSession session = req.getSession();
        String language = (String) session.getAttribute("language");

        if (language.equals("ru")) {
            session.setAttribute("language", "en");
            session.setAttribute("languageButtonLabel", "RU");
            log.info("Attribute language is set to `en`");

        }
        else {
            session.setAttribute("language", "ru");
            session.setAttribute("languageButtonLabel", "EN");
            log.info("Attribute language is set to `ru`");
        }
        System.out.println(req.getRequestURL());
        System.out.println(req.getRequestURI());
        page = "/main.jsp";
        return page;
    }
}

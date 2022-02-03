package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The command that is responsible for switching language.
 * Attributes 'language' and 'languagesButtonLabel' are being edited.
 * pageQuery attribute is being used for building the path to the page from which the command was called
 *
 */
public class ChangeLanguageCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(ChangeLanguageCommand.class);
    String page = null;

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String language = (String) session.getAttribute("language");

        if (language.equals("ru")) {
            session.setAttribute("language", "en");
            session.setAttribute("languageButtonLabel", "RU");
            log.info("Attribute language is set to `en`");

        }
        else if (language.equals("en")) {
            session.setAttribute("language", "ru");
            session.setAttribute("languageButtonLabel", "EN");
            log.info("Attribute language is set to `ru`");
        }

        String queryString = Optional.ofNullable(req.getSession().getAttribute("pageQuery"))
                .map(Object::toString)
                .map(String::trim)
                .orElse("");

        if (queryString != "") page = "/controller?" + queryString;
        else page = ConfigurationManager.getProperty("path.page.index");

        log.info("Going back to" + page);
        return page;
    }

}

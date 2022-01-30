package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";

    private final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        log.info("Authorization process started");

        if (userService.authorize(email,password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", email);
            session.setAttribute("role","User");
            session.setAttribute("userIsAuthorized", true);
            page = ConfigurationManager.getProperty("path.page.main");
            log.info("Authorization process finished successfully");
        }
        else {
            req.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
            log.info("Authorization process failed");
        }
        return page;
    }
}

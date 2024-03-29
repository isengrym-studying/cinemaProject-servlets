package com.example.cinema.controller.comand.common;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command that is responsible for getting and sending back user authorization result
 * If authorization was successful, user object is set as attribute
 *
 */
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

        log.info("Authorization process for potential user with email +" + email + "+started");

        if (userService.authorize(email,password)) {
            User user = userService.getUserInstance(email);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            page = ConfigurationManager.getProperty("path.page.main");
            log.info("Authorization process for user with email " + email + " finished successfully");
        }
        else {
            req.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginError"));
            page = ConfigurationManager.getProperty("path.page.login");
            log.warn("Authorization process for user " + email + " failed.");
        }

        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}

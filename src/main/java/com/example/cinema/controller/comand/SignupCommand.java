package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SignupCommand implements ActionCommand{
    private static Logger log = Logger.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_ROLE = "role";

    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        String name = req.getParameter(PARAM_NAME_NAME);
        String surname = req.getParameter(PARAM_NAME_SURNAME);
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String role = req.getParameter(PARAM_NAME_ROLE);

        User user = new User(name,surname,email,password,role);
        List<String> issues = userService.getSignUpIssues(user);

        log.info("Registration process started");
        if (issues.size() == 0) {
            userService.signUp(user);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role","User");
            session.setAttribute("userIsAuthorized", true);
            page = ConfigurationManager.getProperty("path.page.main");
            log.info("Registration process finished successfully");
        }
        else {
            for (String issue : issues) {
                req.setAttribute(issue, MessageManager.getProperty("message."+issue));
            }

            page = ConfigurationManager.getProperty("path.page.registration");
            log.info("Registration process failed");
        }
        return page;
    }
}

package com.example.cinema.controller.comand.common;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Role;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * The command that is responsible for getting and sending back user registration result
 * If registration was successful, user object is set as attribute
 *
 */
public class SignupCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";

    private static final UserService service = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        String name = req.getParameter(PARAM_NAME_NAME);
        String surname = req.getParameter(PARAM_NAME_SURNAME);
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String role = Role.USER.getString();

        log.info("Registration process started");

        if (service.signUpDataValid(name,surname,email,password)) {
            service.signUp(name,surname,email,password,role);
            User user = service.getUserInstance(email);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            page = ConfigurationManager.getProperty("path.page.main");
            log.info("Registration process finished successfully");
        }
        else {
            req.setAttribute("registrationError", MessageManager.getProperty("message.registrationError"));
            if (service.checkUserExistence(email)) req.setAttribute("userAlreadyExist", MessageManager.getProperty("message.userAlreadyExists"));
            page = ConfigurationManager.getProperty("path.page.registration");
            log.warn("Registration process failed");
        }


        return page;
    }
}

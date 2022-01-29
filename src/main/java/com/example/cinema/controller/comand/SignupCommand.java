package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

public class SignupCommand implements ActionCommand{
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_ROLE = "role";

    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String page = null;

        String name = req.getParameter(PARAM_NAME_NAME);
        String surname = req.getParameter(PARAM_NAME_SURNAME);
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String role = req.getParameter(PARAM_NAME_ROLE);


        if (userService.signUp(name, surname, email, password, role)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", email);
            session.setAttribute("role","User");
            session.setAttribute("userIsAuthorized", true);
            page = ConfigurationManager.getProperty("path.page.main");
        }
        else {
            req.setAttribute("errorRegistrationMessage", MessageManager.getProperty("message.signuperror"));
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        return page;
    }
}

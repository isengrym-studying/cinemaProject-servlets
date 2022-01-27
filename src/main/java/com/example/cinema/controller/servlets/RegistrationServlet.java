package com.example.cinema.controller.servlets;

import com.example.cinema.model.entity.User;
import com.example.cinema.validator.EmailValidator;
import com.example.cinema.validator.NameValidator;
import com.example.cinema.validator.PasswordValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value="/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req,resp);
    }
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("name");
        String userSurname = req.getParameter("surname");
        String userEmail = req.getParameter("email");
        String userPassword = req.getParameter("password");

        User user = new User(userName, userSurname, userEmail, userPassword, "User");

        if (user.checkUserExistence()) {
            req.setAttribute("registrationStatus", "User with such email already exists");
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        }
        if (!EmailValidator.validate(userEmail)) {
            req.setAttribute("registrationStatus", "That doesn't look like email");
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        }
        if (!PasswordValidator.validate(userPassword)) {
            req.setAttribute("registrationStatus", "Password should contain letters, numbers and be longer than 6 symbols");
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        }
        if (!NameValidator.validate(userName) || !NameValidator.validate(userSurname)) {
            req.setAttribute("registrationStatus", "Name and surname can't contain anything except letters");
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        }

        if (EmailValidator.validate(userEmail) && PasswordValidator.validate(userPassword)
                && NameValidator.validate(userName) && NameValidator.validate(userSurname) && !user.checkUserExistence()) {

            user.register();

            HttpSession session = req.getSession();
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("role", user.getRole());
            session.setAttribute("userIsAuthorized", true);

            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("registrationStatus", "Registration wasn't successful. Some fields are not filled correctly");
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        }

    }
}

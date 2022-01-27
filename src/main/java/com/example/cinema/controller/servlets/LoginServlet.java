package com.example.cinema.controller.servlets;

import com.example.cinema.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value="/login")
public class LoginServlet extends HttpServlet {
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
        String userEmail = req.getParameter("email");
        String userPassword = req.getParameter("password");

        if (User.authorize(userEmail, userPassword)) {
            User user = new User(User.getUserByEmail(userEmail));

            HttpSession session = req.getSession();
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("role", user.getRole());
            session.setAttribute("userIsAuthorized", true);

            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("loginStatus", "Authorization wasn't successful");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }
}

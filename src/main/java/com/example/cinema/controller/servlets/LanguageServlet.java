package com.example.cinema.controller.servlets;

import com.example.cinema.model.entity.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(value = "/changeLanguage")
public class LanguageServlet extends HttpServlet {


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
        String language = req.getParameter("languageList");
        HttpSession session = req.getSession();
        session.setAttribute("language", language);

        req.getRequestDispatcher("/").forward(req,resp);
    }
}
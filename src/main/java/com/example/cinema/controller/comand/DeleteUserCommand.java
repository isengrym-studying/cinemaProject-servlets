package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {

        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        UserService service = UserService.getInstance();
        service.deleteUser(user);
        req.getSession().invalidate();
        page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}

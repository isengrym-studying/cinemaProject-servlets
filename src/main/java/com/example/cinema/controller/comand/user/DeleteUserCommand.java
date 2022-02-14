package com.example.cinema.controller.comand.user;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * The command that is responsible for deleting user
 *
 */
public class DeleteUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {

        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (!AccessStatus.isUser(user)) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        UserService service = UserService.getInstance();
        service.deleteUser(user);
        req.getSession().invalidate();
        page = ConfigurationManager.getProperty("path.page.index");
        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}

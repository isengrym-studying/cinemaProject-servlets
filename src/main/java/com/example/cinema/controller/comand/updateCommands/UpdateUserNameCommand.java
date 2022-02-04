package com.example.cinema.controller.comand.updateCommands;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.LoginCommand;
import com.example.cinema.controller.comand.ProfileCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.CipherService;
import com.example.cinema.model.service.UserService;
import com.example.cinema.model.service.validator.NameValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class UpdateUserNameCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);

    final String PARAM_NAME_NAME = "name";
    final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req) {

        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        ActionCommand.pageAdress(req);

        String name = req.getParameter(PARAM_NAME_NAME);
        String password = req.getParameter(PARAM_NAME_PASSWORD);


        UserService userService = UserService.getInstance();
        CipherService cipherService = CipherService.getInstance();

        byte[] salt = user.getSalt();
        byte[] passwordEncrypted = cipherService.getEncryptedPassword(password, salt);

        if (NameValidator.validate(name) && Arrays.equals(passwordEncrypted, user.getPassword())) {
            user.setName(name);
            userService.updateUser(user);

            ProfileCommand profileCommand = new ProfileCommand();
            page = profileCommand.execute(req);

            return page;
        }
        else if (!NameValidator.validate(name)) {
            req.setAttribute("errorNameMessage", MessageManager.getProperty("message.update.nameError"));
        }
        else if (!Arrays.equals(passwordEncrypted, user.getPassword())) {
            req.setAttribute("errorOldPasswordMessage", MessageManager.getProperty("message.update.oldPasswordError"));
        }

        page = ConfigurationManager.getProperty("path.page.nameUpdate");
        return page;

    }
}
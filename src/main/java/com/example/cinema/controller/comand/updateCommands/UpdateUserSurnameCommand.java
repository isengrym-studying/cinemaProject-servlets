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
import com.example.cinema.model.service.validator.PasswordValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class UpdateUserSurnameCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);

    final String PARAM_NAME_SURNAME = "surname";
    final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        ActionCommand.pageAdress(req);

        String surname = req.getParameter(PARAM_NAME_SURNAME);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        User user = (User) req.getSession().getAttribute("user");
        UserService userService = UserService.getInstance();
        CipherService cipherService = CipherService.getInstance();

        byte[] salt = user.getSalt();
        byte[] passwordEncrypted = cipherService.getEncryptedPassword(password, salt);

        if (NameValidator.validate(surname) && Arrays.equals(passwordEncrypted, user.getPassword())) {
            userService.updateUser(user);
            user.setSurname(surname);

            ProfileCommand profileCommand = new ProfileCommand();
            page = profileCommand.execute(req);

            return page;
        }
        else if (!NameValidator.validate(surname)) {
            req.setAttribute("errorSurnameMessage", MessageManager.getProperty("message.update.surnameError"));
        }
        else if (Arrays.equals(passwordEncrypted, user.getPassword())) {
            req.setAttribute("errorOldPasswordMessage", MessageManager.getProperty("message.update.oldPasswordError"));
        }

        page = ConfigurationManager.getProperty("path.page.surnameUpdate");
        return page;

    }
}
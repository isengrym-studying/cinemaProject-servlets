package com.example.cinema.controller.comand.user.updateCommands;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.common.LoginCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.CipherService;
import com.example.cinema.service.UserService;
import com.example.cinema.service.validator.NameValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * The command that is responsible for updating user surname
 *
 */
public class UpdateUserSurnameCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);

    final String PARAM_NAME_SURNAME = "surname";
    final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (!AccessStatus.isUser(user)) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }


        String surname = req.getParameter(PARAM_NAME_SURNAME);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        UserService userService = UserService.getInstance();
        CipherService cipherService = CipherService.getInstance();

        byte[] salt = user.getSalt();
        byte[] passwordEncrypted = cipherService.getEncryptedPassword(password, salt);

        if (NameValidator.validate(surname) && Arrays.equals(passwordEncrypted, user.getPassword())) {
            user.setSurname(surname);
            userService.updateUser(user);

            page = "/controller?command=profile";

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
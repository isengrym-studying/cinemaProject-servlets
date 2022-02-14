package com.example.cinema.controller.comand.user.updateCommands;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.common.LoginCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.CipherService;
import com.example.cinema.service.UserService;
import com.example.cinema.service.validator.EmailValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * The command that is responsible for updating user email
 *
 */
public class UpdateUserEmailCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);

    final String PARAM_NAME_EMAIL = "email";
    final String PARAM_NAME_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (!AccessStatus.isUser(user)) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }


        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        UserService userService = UserService.getInstance();
        CipherService cipherService = CipherService.getInstance();

        byte[] salt = user.getSalt();
        byte[] passwordEncrypted = cipherService.getEncryptedPassword(password, salt);

        if (EmailValidator.validate(email) && Arrays.equals(passwordEncrypted, user.getPassword()) && !userService.checkUserExistence(email)) {
            user.setEmail(email);
            userService.updateUser(user);

            page = "/controller?command=profile";

            return page;
        }
        else if (!EmailValidator.validate(email)) {
            req.setAttribute("errorEmailMessage", MessageManager.getProperty("message.update.emailError"));
        }
        else if (!Arrays.equals(passwordEncrypted, user.getPassword())) {
            req.setAttribute("errorOldPasswordMessage", MessageManager.getProperty("message.update.oldPasswordError"));
        }
        else if (userService.checkUserExistence(email)) {
            req.setAttribute("errorOldPasswordMessage", MessageManager.getProperty("message.userAlreadyExists"));
        }

        page = ConfigurationManager.getProperty("path.page.emailUpdate");
        return page;
    }

}
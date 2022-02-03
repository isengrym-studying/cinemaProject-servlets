package com.example.cinema.controller.comand.updateCommands;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.LoginCommand;
import com.example.cinema.controller.comand.ProfileCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.CipherService;
import com.example.cinema.model.service.UserService;
import com.example.cinema.model.service.validator.EmailValidator;
import com.example.cinema.model.service.validator.NameValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


public class UpdateUserEmailCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);

    final String PARAM_NAME_EMAIL = "email";
    final String PARAM_NAME_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        ActionCommand.pageAdress(req);

        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        User user = (User) req.getSession().getAttribute("user");

        UserService userService = UserService.getInstance();
        CipherService cipherService = CipherService.getInstance();

        byte[] salt = user.getSalt();
        byte[] passwordEncrypted = cipherService.getEncryptedPassword(password, salt);


        if (EmailValidator.validate(email) && Arrays.equals(passwordEncrypted, user.getPassword())) {
            userService.updateUser(user);
            user.setEmail(email);

            ProfileCommand profileCommand = new ProfileCommand();
            page = profileCommand.execute(req);

            return page;

        }
        else if (!EmailValidator.validate(email)) {
            req.setAttribute("errorEmailMessage", MessageManager.getProperty("message.update.emailError"));
        }
        else if (!Arrays.equals(passwordEncrypted, user.getPassword())) {
            req.setAttribute("errorOldPasswordMessage", MessageManager.getProperty("message.update.oldPasswordError"));
        }


        page = ConfigurationManager.getProperty("path.page.emailUpdate");
        return page;
    }
}
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
import com.example.cinema.model.service.validator.PasswordValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class UpdateUserPasswordCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);

    final String PARAM_NAME_NEWPASSWORD = "newPassword";
    final String PARAM_NAME_OLDPASSWORD = "oldPassword";
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        ActionCommand.pageAdress(req);

        String newPassword = req.getParameter(PARAM_NAME_NEWPASSWORD);
        String oldPassword = req.getParameter(PARAM_NAME_OLDPASSWORD);

        UserService userService = UserService.getInstance();
        CipherService cipherService = CipherService.getInstance();

        byte[] oldPasswordEncrypted = cipherService.getEncryptedPassword(oldPassword, user.getSalt());
        byte[] newPasswordEncrypted = cipherService.getEncryptedPassword(newPassword, user.getSalt());

        if (PasswordValidator.validate(newPassword) && Arrays.equals(oldPasswordEncrypted, user.getPassword())) {
            user.setPassword(newPasswordEncrypted);
            userService.updateUser(user);


            ProfileCommand profileCommand = new ProfileCommand();
            page = profileCommand.execute(req);

            return page;
        }
        else if (!PasswordValidator.validate(newPassword)) {
            req.setAttribute("errorPasswordMessage", MessageManager.getProperty("message.update.passwordError"));
        }
        else if (!Arrays.equals(oldPasswordEncrypted, user.getPassword())) {
            req.setAttribute("errorOldPasswordMessage", MessageManager.getProperty("message.update.oldPasswordError"));
        }

        page = ConfigurationManager.getProperty("path.page.passwordUpdate");
        return page;
    }
}
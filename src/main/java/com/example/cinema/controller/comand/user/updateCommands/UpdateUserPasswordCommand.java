package com.example.cinema.controller.comand.user.updateCommands;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.MessageManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.common.LoginCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.CipherService;
import com.example.cinema.service.UserService;
import com.example.cinema.service.validator.PasswordValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * The command that is responsible for updating user password
 *
 */
public class UpdateUserPasswordCommand implements ActionCommand {
    private static Logger log = Logger.getLogger(LoginCommand.class);

    final String PARAM_NAME_NEWPASSWORD = "newPassword";
    final String PARAM_NAME_OLDPASSWORD = "oldPassword";
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (!AccessStatus.isUser(user)) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }


        String newPassword = req.getParameter(PARAM_NAME_NEWPASSWORD);
        String oldPassword = req.getParameter(PARAM_NAME_OLDPASSWORD);

        UserService userService = UserService.getInstance();
        CipherService cipherService = CipherService.getInstance();

        byte[] oldPasswordEncrypted = cipherService.getEncryptedPassword(oldPassword, user.getSalt());
        byte[] newPasswordEncrypted = cipherService.getEncryptedPassword(newPassword, user.getSalt());

        if (PasswordValidator.validate(newPassword) && Arrays.equals(oldPasswordEncrypted, user.getPassword())) {
            user.setPassword(newPasswordEncrypted);
            userService.updateUser(user);


            page = "/controller?command=profile";

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
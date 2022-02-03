package com.example.cinema.controller.comand;

import com.example.cinema.controller.comand.updateCommands.*;

/**
 * Enum of all existing commands
 *
 */
public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();

        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SIGNUP {
        {
            this.command = new SignupCommand();
        }
    },
    GETFILMS {
        {
            this.command = new GenerateMoviePage();
        }
    },
    CHANGELANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    },
    GETFULLMOVIE{
        {
            this.command = new GenerateMoviePageCommand();
        }
    },
    GETSEANCES{
        {
            this.command = new GenerateSeancesPage();
        }
    },
    FILLMAINPAGE{
        {
            this.command = new MainPageFillerCommand();
        }
    },
    PROFILE{
        {
            this.command = new ProfileCommand();
        }
    },
    UPDATEUSERNAME {
        {
            this.command = new UpdateUserNameCommand();
        }
    },
    UPDATEUSERSURNAME {
        {
            this.command = new UpdateUserSurnameCommand();
        }
    },
    UPDATEUSEREMAIL {
        {
            this.command = new UpdateUserEmailCommand();
        }
    },
    UPDATEUSERPASSWORD {
        {
            this.command = new UpdateUserPasswordCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}

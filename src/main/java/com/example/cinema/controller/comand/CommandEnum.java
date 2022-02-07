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
    CHANGELANGUAGE {
        {
            this.command = new ChangeLanguage();
        }
    },
    GETFULLMOVIE{
        {
            this.command = new GenerateMoviePage();
        }
    },
    GETMOVIES{
        {
            this.command = new GenerateMoviesPage();
        }
    },
    GETSEANCES{
        {
            this.command = new GenerateSeancesPage();
        }
    },
    FILLMAINPAGE{
        {
            this.command = new FillMainPageCommand();
        }
    },
    PROFILE{
        {
            this.command = new GenerateProfilePage();
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
    TICKETCHOICEPAGE {
        {
            this.command = new GenerateTicketChoicePage();
        }
    },
    CONFIRMTICKET {
        {
            this.command = new TicketConfirmationCommand();
        }
    },
    TICKETPAGE {
        {
            this.command = new GenerateTicketPage();
        }
    },
    DELETEUSER {
        {
            this.command = new DeleteUserCommand();
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

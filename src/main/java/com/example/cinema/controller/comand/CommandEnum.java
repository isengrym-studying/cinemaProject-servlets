package com.example.cinema.controller.comand;

import com.example.cinema.controller.comand.admin.*;
import com.example.cinema.controller.comand.common.*;
import com.example.cinema.controller.comand.user.*;
import com.example.cinema.controller.comand.user.reviewCommands.AddReviewCommand;
import com.example.cinema.controller.comand.user.reviewCommands.DeleteReviewCommand;
import com.example.cinema.controller.comand.user.reviewCommands.UpdateReviewCommand;
import com.example.cinema.controller.comand.user.updateCommands.UpdateUserEmailCommand;
import com.example.cinema.controller.comand.user.updateCommands.UpdateUserNameCommand;
import com.example.cinema.controller.comand.user.updateCommands.UpdateUserPasswordCommand;
import com.example.cinema.controller.comand.user.updateCommands.UpdateUserSurnameCommand;

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
    },
    NEWMOVIEPAGE {
        {
            this.command = new GenerateNewMoviePage();
        }
    },
    ADDNEWMOVIE {
        {
            this.command = new AddMovieCommand();
        }
    },
    DELETEMOVIE {
        {
            this.command = new DeleteMovieCommand();
        }
    },
    NEWSEANCEPAGE {
        {
            this.command = new GenerateNewSeancePage();
        }
    },
    ADDNEWSEANCE {
        {
            this.command = new AddSeanceCommand();
        }
    },
    DELETESEANCE {
        {
            this.command = new DeleteSeanceCommand();
        }
    },
    ADDREVIEW {
        {
            this.command = new AddReviewCommand();
        }
    },
    UPDATEREVIEW {
        {
            this.command = new UpdateReviewCommand();
        }
    },
    DELETEREVIEW {
        {
            this.command = new DeleteReviewCommand();
        }
    },
    DELETEUSERREVIEW {
        {
            this.command = new DeleteUserReviewCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}

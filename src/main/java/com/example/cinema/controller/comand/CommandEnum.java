package com.example.cinema.controller.comand;

import com.example.cinema.controller.comand.language.ChangeLanguageCommand;
import com.example.cinema.controller.comand.language.SetLanguageCommand;

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
            this.command = new GetMoviesCommand();
        }
    },
    CHANGELANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    },
    SETLANGUAGE{
        {
            this.command = new SetLanguageCommand();
        }
    },
    GETFULLMOVIE{
        {
            this.command = new GetCertainMovieCommand();
        }
    },
    GETSEANCES{
        {
            this.command = new GetSeancesCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}

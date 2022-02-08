package com.example.cinema.controller.comand.admin;

import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.service.MovieSeanceService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AddSeanceCommand implements ActionCommand {

    private static Logger log = Logger.getLogger(AddMovieCommand.class);
    private static final String PARAM_NAME_TITLE_ID = "movies";
    private static final String PARAM_NAME_DATE = "dateTime";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        int movieId = Integer.parseInt(req.getParameter(PARAM_NAME_TITLE_ID));
        LocalDateTime date = LocalDateTime.parse(req.getParameter(PARAM_NAME_DATE));

        MovieSeanceService service = MovieSeanceService.getInstance();
        service.addSeance(movieId, date.toEpochSecond(ZoneOffset.UTC));
        page = "/controller?command=getseances";

        return page;
    }
}
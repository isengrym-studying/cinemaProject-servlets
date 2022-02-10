package com.example.cinema.controller.comand.common;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The command that is responsible for generating definite movie page
 *
 */
public class GenerateMoviePage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;


        MovieSeanceService service = MovieSeanceService.getInstance();
        int movieId = Integer.parseInt(req.getParameter("movieId"));

        Movie movie = service.getMovieById(movieId);

        List<Seance> seancesList = service.getCertainMovieSeances(movie);

        List<LocalDate> dates = service.getDatesOfSeances(seancesList);
        Map<LocalDate,List<Seance>> mapDateSeance = service.groupSeancesByDate(dates,seancesList);
        req.setAttribute("movie", movie);
        req.setAttribute("durationMin",movie.getDuration().getSeconds()/60);

        req.setAttribute("seancesMap", mapDateSeance);
        page = ConfigurationManager.getProperty("path.page.movie");


//        ActionCommand.pageAdress(req);
        return page;
    }



}

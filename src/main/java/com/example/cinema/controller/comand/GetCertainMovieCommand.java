package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class GetCertainMovieCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        MovieSeanceService service = MovieSeanceService.getInstance();
        int movieId = Integer.parseInt(req.getParameter("movieId"));
        Movie movie = service.getMovieById(movieId);
        List<Seance> seanceList = service.getCertainMovieSeances(movie);
        List<Seance> futureSeanceList = service.getOnlyFutureSeances(seanceList);
        List<LocalDate> dates = service.getDatesOfSeances(futureSeanceList);
        Map<LocalDate,List<Seance>> mapDateSeance = service.groupSeancesByDate(dates,futureSeanceList);


        req.setAttribute("movie", movie);
        req.setAttribute("durationMin",movie.getDuration().getSeconds()/60);
        req.setAttribute("seances", futureSeanceList);
        req.setAttribute("seancesMap", mapDateSeance);

        page = ConfigurationManager.getProperty("path.page.movie");
        return page;
    }


}
package com.example.cinema.controller.comand.admin;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Role;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GenerateNewSeancePage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        User user = (User)req.getSession().getAttribute("user");
        if (user == null || !user.getRole().equals(Role.ADMIN.getString())) {
            page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }


        MovieSeanceService movieService = MovieSeanceService.getInstance();

        List<Movie> movies = movieService.getAllMovies();
        req.setAttribute("movies",movies);

        page = ConfigurationManager.getProperty("path.page.newSeance");
        return page;
    }
}

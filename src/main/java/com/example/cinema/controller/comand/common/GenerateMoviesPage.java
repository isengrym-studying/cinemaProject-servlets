package com.example.cinema.controller.comand.common;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Role;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.MovieSeanceService;
import com.example.cinema.model.service.PaginationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The command that is responsible for generating page with all movies
 *
 */
public class GenerateMoviesPage implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        MovieSeanceService service = MovieSeanceService.getInstance();
        PaginationService paginationService = PaginationService.getInstance();
        User user;

        int moviePage;
        int totalOnPage = 8;
        if (req.getSession().getAttribute("user") != null) {
            user = (User) req.getSession().getAttribute("user");
            if (user.getRole().equalsIgnoreCase(Role.ADMIN.getString())) totalOnPage -= 1;
        }

        if (req.getParameter("moviePage") == null) moviePage = 1;
        else moviePage = Integer.parseInt(req.getParameter("moviePage"));

        int moviesQuantity;
        int moviePagesQuantity;

        List<Movie> list;

        String movieView = req.getParameter("view");
        if (movieView == null || movieView.equalsIgnoreCase("futureOnly")) {
            list = service.getMoviesWithSeancesPaginated((moviePage-1)*totalOnPage, totalOnPage);
            moviesQuantity = service.getUniqueSeancesQuantity();
            req.setAttribute("view","futureOnly");
        }
        else {
            list = service.getAllMoviesPaginated((moviePage-1)*totalOnPage, totalOnPage);
            moviesQuantity = service.getMoviesQuantity();
            req.setAttribute("view","all");
        }

        moviePagesQuantity = paginationService.сountPagesQuantity(totalOnPage, moviesQuantity);

        req.setAttribute("movies", list);
        req.setAttribute("moviePagesQuantity",moviePagesQuantity);

        page = ConfigurationManager.getProperty("path.page.movies");

        return page;
    }


}

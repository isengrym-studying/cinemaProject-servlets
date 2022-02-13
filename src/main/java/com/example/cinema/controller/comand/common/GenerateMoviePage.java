package com.example.cinema.controller.comand.common;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Review;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.MovieSeanceService;
import com.example.cinema.model.service.PaginationService;
import com.example.cinema.model.service.ReviewService;

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


        MovieSeanceService movieSeanceservice = MovieSeanceService.getInstance();
        int movieId = Integer.parseInt(req.getParameter("movieId"));

        Movie movie = movieSeanceservice.getMovieById(movieId);

        List<Seance> seancesList = movieSeanceservice.getCertainMovieSeances(movie);

        List<LocalDate> dates = movieSeanceservice.getDatesOfSeances(seancesList);
        Map<LocalDate,List<Seance>> mapDateSeance = movieSeanceservice.groupSeancesByDate(dates,seancesList);
        req.setAttribute("movie", movie);
        req.setAttribute("durationMin",movie.getDuration().getSeconds()/60);
        req.setAttribute("seancesMap", mapDateSeance);

        ReviewService reviewService = ReviewService.getInstance();
        PaginationService paginationService = PaginationService.getInstance();
        int reviewPage;
        int totalOnPage = 6;

        if (req.getSession().getAttribute("user") != null){
            User user = (User)req.getSession().getAttribute("user");
            Review review = reviewService.getReviewByUserId(user.getId(), movie.getId());
            if (review.getUser() != null) {
                req.setAttribute("reviewExists", true);
                req.setAttribute("userReviewText", review.getText());
            }
            else req.setAttribute("reviewExists", false);
        }


        if (req.getParameter("commentPage") == null) reviewPage = 1;
        else reviewPage = Integer.parseInt(req.getParameter("commentPage"));

        int reviewQuantity;
        int reviewPagesQuantity;

        List<Review> reviews = reviewService.getReviewsForMovie(movie.getId(),(reviewPage-1)*totalOnPage, totalOnPage);
        reviewQuantity = reviewService.getReviewsQuantity(movie.getId());
        reviewPagesQuantity = paginationService.сountPagesQuantity(totalOnPage,reviewQuantity);

        req.setAttribute("reviews", reviews);
        req.setAttribute("reviewPagesQuantity",reviewPagesQuantity);


        page = ConfigurationManager.getProperty("path.page.movie");


//        ActionCommand.pageAdress(req);
        return page;
    }



}

package com.example.cinema.controller.comand.user.reviewCommands;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.common.GenerateMoviePage;
import com.example.cinema.controller.comand.common.GenerateMoviesPage;
import com.example.cinema.model.entity.Review;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.ReviewService;

import javax.servlet.http.HttpServletRequest;

public class AddReviewCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (!AccessStatus.isUser(user)) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }
        ReviewService reviewService = ReviewService.getInstance();

        String reviewText = req.getParameter("reviewText");
        int movieId = Integer.parseInt(req.getParameter("movieId"));

        Review review = new Review();
        review.setMovieId(movieId);
        review.setUser(user);
        review.setText(reviewText);
        if (reviewService.reviewIsValid(review)) reviewService.addReview(review);

        GenerateMoviePage command = new GenerateMoviePage();
        page = command.execute(req);
        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}

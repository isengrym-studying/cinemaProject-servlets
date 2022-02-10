package com.example.cinema.controller.comand.common;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.service.MovieSeanceService;
import com.example.cinema.model.service.PaginationService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The command that is responsible for generating page with all seances
 *
 */
public class GenerateSeancesPage implements ActionCommand {
    private static final String SORTING_BY_DATE = "date";
    private static final String SORTING_BY_SEATS = "freeSeats";
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        MovieSeanceService movieSeanceService = MovieSeanceService.getInstance();
        PaginationService paginationService = PaginationService.getInstance();
        List<Movie> movies = movieSeanceService.getAllMovies();
        req.setAttribute("movies",movies);

        int movieId;
        String sorting;
        String order;

        if (req.getParameter("movieId") != null) movieId = Integer.parseInt(req.getParameter("movieId"));
        else movieId = -1;
        if (req.getParameter("sorting") != null) sorting = req.getParameter("sorting");
        else sorting = SORTING_BY_DATE;
        if (req.getParameter("order") != null) order = req.getParameter("order");
        else order = "ASC";

        int seancePage;
        int totalOnPage = 5;
        int seancesQuantity;
        int seancePagesQuantity;
        if (req.getParameter("seancePage") == null) seancePage = 1;
        else seancePage = Integer.parseInt(req.getParameter("seancePage"));

        List<Seance> list = movieSeanceService.getSeancesByParametersPaginated(movieId,sorting,order,(seancePage-1)*totalOnPage, totalOnPage);
        seancesQuantity = movieSeanceService.getSeancesQuantityByParameters(movieId);
        seancePagesQuantity = paginationService.сountPagesQuantity(totalOnPage, seancesQuantity);

        req.setAttribute("seances", list);

        req.setAttribute("movieId",movieId);
        req.setAttribute("sorting",sorting);
        req.setAttribute("order",order);

        req.setAttribute("seancePagesQuantity",seancePagesQuantity);
        req.setAttribute("pageTitleProperty", "seances.title");
        req.setAttribute("pageHeadlineProperty", "seances.headline");

        page = ConfigurationManager.getProperty("path.page.seances");

        return page;
    }
}

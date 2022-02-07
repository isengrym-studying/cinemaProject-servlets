package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The command that is responsible for getting and sending back all films, that have active seances
 *
 */
public class GenerateMoviesPage implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        MovieSeanceService service = MovieSeanceService.getInstance();

        int moviePage;
        int totalOnPage = 12;

        if (req.getParameter("moviePage") == null) moviePage = 1;
        else moviePage = Integer.parseInt(req.getParameter("moviePage"));

        List<Seance> list = service.getUniqueFutureSeancesPaginated((moviePage-1)*totalOnPage, totalOnPage);


        int moviesQuantity = service.getUniqueSeancesQuantity();
        int moviePagesQuantity;

        if (moviesQuantity % totalOnPage != 0) moviePagesQuantity = moviesQuantity/totalOnPage + 1;
        else moviePagesQuantity = moviesQuantity/totalOnPage;

        req.setAttribute("seances", list);
        req.setAttribute("moviePagesQuantity",moviePagesQuantity);

        req.setAttribute("pageTitleProperty", "seances.title");
        req.setAttribute("pageHeadlineProperty", "seances.headline");

        page = ConfigurationManager.getProperty("path.page.movies");

        return page;
    }


}

package com.example.cinema.controller.comand.admin;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.common.GenerateMoviesPage;
import com.example.cinema.controller.comand.common.GenerateSeancesPage;
import com.example.cinema.model.entity.Role;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.MovieSeanceService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * The command that is responsible for adding new seances
 *
 */
public class AddSeanceCommand implements ActionCommand {

    private static Logger log = Logger.getLogger(AddMovieCommand.class);
    private static final String PARAM_NAME_TITLE_ID = "movies";
    private static final String PARAM_NAME_DATE = "dateTime";
    private static final String PARAM_NAME_PRICE = "price";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User)req.getSession().getAttribute("user");

        if (!AccessStatus.isAdmin(user)) {
            page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }

        int movieId = Integer.parseInt(req.getParameter(PARAM_NAME_TITLE_ID));
        LocalDateTime date = LocalDateTime.parse(req.getParameter(PARAM_NAME_DATE));
        int price = Integer.parseInt(req.getParameter(PARAM_NAME_PRICE));

        MovieSeanceService service = MovieSeanceService.getInstance();
        service.addSeance(movieId, date.toEpochSecond(ZoneOffset.UTC),price);

        GenerateSeancesPage command = new GenerateSeancesPage();
        page = command.execute(req);

        return page;
    }
}
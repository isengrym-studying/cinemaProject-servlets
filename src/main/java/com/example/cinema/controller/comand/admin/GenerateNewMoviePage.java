package com.example.cinema.controller.comand.admin;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Genre;
import com.example.cinema.model.entity.Role;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The command that is responsible for generating movie creating page
 *
 */
public class GenerateNewMoviePage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        User user = (User)req.getSession().getAttribute("user");
        if (!AccessStatus.isAdmin(user)) {
            page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }

        GenreService genreService = GenreService.getInstance();

        List<Genre> genres = genreService.getAllGenres();
        req.setAttribute("genres",genres);

        page = ConfigurationManager.getProperty("path.page.newMovie");
        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}

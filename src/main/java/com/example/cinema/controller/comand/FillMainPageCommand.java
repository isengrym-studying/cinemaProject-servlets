package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.Movie;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 *
 *
 */
public class FillMainPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        MovieSeanceService service = MovieSeanceService.getInstance();
        page = ConfigurationManager.getProperty("path.page.main");

        return page;
    }
}
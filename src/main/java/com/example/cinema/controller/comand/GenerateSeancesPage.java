package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

public class GenerateSeancesPage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;

        MovieSeanceService service = MovieSeanceService.getInstance();

        int seancePage;
        int totalOnPage = 5;

        if (req.getParameter("seancePage") == null) seancePage = 1;
        else seancePage = Integer.parseInt(req.getParameter("seancePage"));

        List<Seance> list = service.getFutureSeancesPaginated((seancePage-1)*totalOnPage, totalOnPage);

        int seancesQuantity = service.getFutureSeancesQuantity();
        int seancePagesQuantity;

        if (seancesQuantity % totalOnPage != 0) seancePagesQuantity = seancesQuantity/totalOnPage + 1;
        else seancePagesQuantity = seancesQuantity/totalOnPage;

        req.setAttribute("seances", list);
        req.setAttribute("seancePagesQuantity",seancePagesQuantity);
        req.setAttribute("pageTitleProperty", "seances.title");
        req.setAttribute("pageHeadlineProperty", "seances.headline");

        page = ConfigurationManager.getProperty("path.page.seances");

        return page;
    }
}

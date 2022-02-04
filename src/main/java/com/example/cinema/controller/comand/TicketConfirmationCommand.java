package com.example.cinema.controller.comand;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.entity.Ticket;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class TicketConfirmationCommand implements  ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        if (action.equals("confirm")) {
            TicketService service = TicketService.getInstance();
            Ticket ticket = new Ticket();
            Seance seance = (Seance)session.getAttribute("seance");

            ticket.setRowNumber((Integer) session.getAttribute("rowId"));
            ticket.setPlaceNumber((Integer) session.getAttribute("placeId"));
            ticket.setSeanceId(seance.getId());
            ticket.setUserId(user.getId());

            service.createTicket(ticket);
        }

        page = "/controller?"+session.getAttribute("pageQuery");

        System.out.println(page);
        session.removeAttribute("seance");
        session.removeAttribute("rowId");
        session.removeAttribute("placeId");

        ActionCommand.pageAdress(req);
        return page;
    }
}

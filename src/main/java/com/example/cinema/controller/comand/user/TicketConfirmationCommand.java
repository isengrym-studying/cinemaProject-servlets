package com.example.cinema.controller.comand.user;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.entity.Ticket;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command is responsible for buying ticket
 *
 */
public class TicketConfirmationCommand implements ActionCommand {
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
        Seance seance = (Seance)session.getAttribute("seance");

        if (action.equals("confirm")) {
            TicketService service = TicketService.getInstance();
            Ticket ticket = new Ticket();
            ticket.setRowNumber((Integer) session.getAttribute("rowId"));
            ticket.setPlaceNumber((Integer) session.getAttribute("placeId"));
            ticket.setSeance(seance);
            ticket.setUserId(user.getId());

            service.createTicket(ticket);
        }

        page = "/controller?command=ticketChoicePage&seanceId="+seance.getId();

        session.removeAttribute("seance");
        session.removeAttribute("rowId");
        session.removeAttribute("placeId");

        return page;
    }
}

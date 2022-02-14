package com.example.cinema.controller.comand.user;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.common.GenerateMoviePage;
import com.example.cinema.controller.comand.common.GenerateTicketChoicePage;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.entity.Ticket;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.TicketService;

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
        if (!AccessStatus.isUser(user)) {
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

        GenerateTicketChoicePage command = new GenerateTicketChoicePage();
        page = command.execute(req);

        session.removeAttribute("seance");
        session.removeAttribute("rowId");
        session.removeAttribute("placeId");

        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}

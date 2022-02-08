package com.example.cinema.controller.comand.user;

import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;

/**
 * The command that is responsible for getting full users information,
 * including registration data and tickets
 *
 */
public class GenerateProfilePage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        req.setAttribute("userName", user.getName());
        req.setAttribute("userSurname", user.getSurname());
        req.setAttribute("userEmail", user.getEmail());

        TicketService ticketService = TicketService.getInstance();

        int ticketPage;
        int totalOnPage = 4;
        int ticketsQuantity = ticketService.getTicketsQuantity(user.getId());
        int ticketPagesQuantity;
        if (ticketsQuantity % totalOnPage != 0) {
            ticketPagesQuantity = ticketsQuantity/totalOnPage + 1;
        }
        else {
            ticketPagesQuantity = ticketsQuantity/totalOnPage;
        }


        if (req.getParameter("ticketPage") == null) {
            ticketPage = 1;
        }
        else {
            ticketPage = Integer.parseInt(req.getParameter("ticketPage"));
        }

        req.setAttribute("ticketList", ticketService.getPaginatedTickets(user.getId(), (ticketPage-1)*totalOnPage, totalOnPage));
        req.setAttribute("ticketPagesQuantity",ticketPagesQuantity);


        page = ConfigurationManager.getProperty("path.page.profile");
        return page;
    }
}

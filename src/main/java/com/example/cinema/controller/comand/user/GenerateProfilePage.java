package com.example.cinema.controller.comand.user;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.PaginationService;
import com.example.cinema.service.TicketService;

import javax.servlet.http.HttpServletRequest;

/**
 * The command that is responsible for generating user profile
 *
 *
 */
public class GenerateProfilePage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User) req.getSession().getAttribute("user");
        if (!AccessStatus.isUser(user)) {
            page = ConfigurationManager.getProperty("path.page.login");
            return page;
        }

        req.setAttribute("userName", user.getName());
        req.setAttribute("userSurname", user.getSurname());
        req.setAttribute("userEmail", user.getEmail());

        TicketService ticketService = TicketService.getInstance();
        PaginationService paginationService = PaginationService.getInstance();

        int ticketPage;
        int totalOnPage = 4;

        if (req.getParameter("ticketPage") == null) ticketPage = 1;
        else ticketPage = Integer.parseInt(req.getParameter("ticketPage"));

        int ticketsQuantity = ticketService.getTicketsQuantity(user.getId());
        int ticketPagesQuantity;

        ticketPagesQuantity = paginationService.сountPagesQuantity(totalOnPage, ticketsQuantity);

        req.setAttribute("ticketList", ticketService.getPaginatedTickets(user.getId(), (ticketPage-1)*totalOnPage, totalOnPage));
        req.setAttribute("ticketPagesQuantity",ticketPagesQuantity);


        page = ConfigurationManager.getProperty("path.page.profile");
        req.getSession().setAttribute("returnPage", page);
        return page;
    }
}

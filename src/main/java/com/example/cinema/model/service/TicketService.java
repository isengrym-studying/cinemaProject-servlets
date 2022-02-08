package com.example.cinema.model.service;

import com.example.cinema.model.dao.TicketDao;
import com.example.cinema.model.entity.Ticket;

import java.util.List;

/**
 * The class contains all the logic for working with tickets.
 * This layer is located between DAO and Controller layers
 *
 */
public class TicketService {
    private final TicketDao ticketDao = TicketDao.getInstance();
    private static TicketService ticketService;

    public static synchronized TicketService getInstance() {
        if (ticketService == null) {
            ticketService = new TicketService();
        }
        return ticketService;
    }

    /**
     * Method for getting tickets for seance, which id is given as parameter.
     * Method just resends to the relevant DAO method and contains no
     * additional logic
     * @param seanceId id of definite seance
     * @return Returns filled List<Ticket> (If tickets were found).
     * Returns empty List<Ticket> (If no tickets were found).
     *
     */
    public List<Ticket> getTicketsForSeance(int seanceId) {
        return ticketDao.getTicketsForSeance(seanceId);
    }

    /**
     * Method for creating tickets, based on information that contains ticket,
     * which is given as parameter. Method just resends to the relevant DAO method and contains no
     * additional logic
     * @param ticket simple ticket object
     * @return Returns true (If there were no issues).
     * Returns false (If there are issues).
     *
     */
    public boolean createTicket(Ticket ticket) {
        return ticketDao.createTicket(ticket);
    }

    /**
     * Method for getting list of tickets (paginated) for user, which id is given as parameter.
     * Method just resends to the relevant DAO method and contains no
     * additional logic
     * @param userId id of the user for which tickets will be searched
     * @param startId index from the record from which the selection of tickets will start
     * @param total quantity of tickets, that are going to be returned
     * @return Returns filled List<Ticket> (If tickets were found).
     * Returns empty List<Ticket> (If no tickets were found).
     *
     */
    public List<Ticket> getPaginatedTickets(int userId, int startId, int total) {
        return ticketDao.getUserTicketsPaginated(userId,startId,total);
    }

    /**
     * Method for getting quantity of tickets of definite user, which id is given as parameter
     * Method just resends to the relevant DAO method and contains no
     * additional logic
     * @return Returns int (quantity of tickets for user)
     *
     */
    public int getTicketsQuantity(int userId) {
        return ticketDao.getTicketsQuantity(userId);
    }
}

package com.example.cinema.model.service;

import com.example.cinema.model.dao.TicketDao;
import com.example.cinema.model.entity.Ticket;

import java.util.List;

public class TicketService {
    private final TicketDao ticketDao = TicketDao.getInstance();
    private static TicketService ticketService;

    public static synchronized TicketService getInstance() {
        if (ticketService == null) {
            ticketService = new TicketService();
        }
        return ticketService;
    }

    public List<Ticket> getTicketsForSeance(int seanceId) {
        return ticketDao.getTicketsForSeance(seanceId);
    }

    public boolean createTicket(Ticket ticket) {
        return ticketDao.createTicket(ticket);
    }
    public List<Ticket> getPaginatedTickets(int userId, int startId, int total) {
        return ticketDao.getUserTicketsPaginated(userId,startId,total);
    }

    public int getTicketsQuantity(int userId) {
        return ticketDao.getTicketsQuantity(userId);
    }
}

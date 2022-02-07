package com.example.cinema.model.dao;

import com.example.cinema.model.connectionpool.ConnectionPool;
import com.example.cinema.model.dao.exceptions.DaoException;
import com.example.cinema.model.entity.Seance;
import com.example.cinema.model.entity.Ticket;
import com.example.cinema.model.service.MovieSeanceService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Data access object for Ticket-entity. (Singleton pattern is implemented)
 *
 */
public class TicketDao {
    private static Logger log = Logger.getLogger(UserDao.class);
    private static TicketDao ticketDao;

    public static synchronized TicketDao getInstance() {
        if (ticketDao == null) {
            ticketDao = new TicketDao();
        }
        return ticketDao;
    }

    private TicketDao() {}

    /**
     * Method is being used to find if there is user with given email value in DB's `users` table
     * @param seanceId id of certain seance, for which the ticket list is going to be formed
     * @return List<Ticket> (if there are tickets for given seance)
     * Empty List<Ticket> (if there are no tickets for given seance)
     * @throws DaoException catches SQLException and throws custom DAO-layer exception
     */
    public List<Ticket> getTicketsForSeance(int seanceId) {
        log.info("Getting tickets from DB, where seance_id = ("+ seanceId + ")");
        List<Ticket> ticketList = new LinkedList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.TicketQuery.GET_TICKETS_FOR_SEANCE)) {

            statement.setInt(1, seanceId);

            try (ResultSet resSet = statement.executeQuery()) {
                while (resSet.next()) {
                    Ticket ticket = new Ticket();
                    SeanceDao seanceDao = SeanceDao.getInstance();

                    ticket.setTicketId(resSet.getInt("ticket_id"));
                    ticket.setUserId(resSet.getInt("user_id"));

                    Seance seance = seanceDao.getSeanceById(resSet.getInt("seance_id"));
                    ticket.setSeance(seance);

                    ticket.setRowNumber(resSet.getInt("row_number"));
                    ticket.setPlaceNumber(resSet.getInt("place_number"));
                    ticket.setPrice(resSet.getInt("price"));
                    ticketList.add(ticket);
                }
                log.info("Tickets were successfully collected");
            }
        } catch (SQLException e) {
            log.error("SQLException in UserDao.checkUserExistence() " + e.getMessage());
            throw new DaoException("Couldn't find user in users table", e);
        }

        return ticketList;
    }

    public boolean createTicket(Ticket ticket) {
        log.info("Adding ticket to DB ("+ ticket + ")");

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.TicketQuery.ADD_TICKET)) {

            statement.setInt(1, ticket.getUserId());
            statement.setInt(2, ticket.getSeance().getId());
            statement.setInt(3, ticket.getRowNumber());
            statement.setInt(4, ticket.getPlaceNumber());

            log.info("Ticket was successfully added to DB");
            return statement.execute();
        } catch (SQLException e) {
            log.error("SQLException in UserDao.checkUserExistence() " + e.getMessage());
            throw new DaoException("Couldn't add ticket to ticket table", e);
        }
    }

    public List<Ticket> getUserTicketsPaginated(int userId, int startItem, int total) {
        log.info("Getting from DB "+ total +" tickets beginning from "+ startItem +" item ");
        List<Ticket> ticketList = new LinkedList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.TicketQuery.GET_USER_TICKETS_PAGINATED)) {

            statement.setInt(1, userId);
            statement.setInt(2, startItem);
            statement.setInt(3, total);

            try (ResultSet resSet = statement.executeQuery()) {
                while (resSet.next()) {
                    Ticket ticket = new Ticket();
                    SeanceDao seanceDao = SeanceDao.getInstance();

                    ticket.setTicketId(resSet.getInt("ticket_id"));
                    ticket.setUserId(resSet.getInt("user_id"));

                    Seance seance = seanceDao.getSeanceById(resSet.getInt("seance_id"));
                    ticket.setSeance(seance);

                    ticket.setRowNumber(resSet.getInt("row_number"));
                    ticket.setPlaceNumber(resSet.getInt("place_number"));
                    ticket.setPrice(resSet.getInt("price"));
                    ticketList.add(ticket);
                }
                log.info(total + " tickets from the"+ startItem + " item were successfully collected");
            }
        } catch (SQLException e) {
            log.error("SQLException in TicketDao.getUserTicketsPaginated() " + e.getMessage());
            throw new DaoException("Couldn't get limited tickets", e);
        }

        return ticketList;
    }

    public int getTicketsQuantity(int id) {
        log.info("Counting tickets for user with id ("+ id + ")");
        int numberOfTickets = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLQuery.TicketQuery.COUNT_USER_TICKETS)) {
            statement.setInt(1, id);
            try (ResultSet resSet = statement.executeQuery()) {
                if (resSet.next()) {
                    numberOfTickets = resSet.getInt("count");
                    log.info("Successfully got number of tickets (" +numberOfTickets +")");
                }
            }

        } catch (SQLException e) {
            log.error("SQLException in TicketDao.getTicketsQuantity() " + e.getMessage());
            throw new DaoException("Couldn't get number of tickets", e);
        }
        return numberOfTickets;
    }
}

package dao;

import models.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketsDao extends AbstractDao<Ticket, Long> {
    @Override
    public Ticket get(Long id) {
        Ticket ticket = null;
        setConnection();
        try {
            ResultSet resultSet = statement.executeQuery(Queries.sqlGetTicketById(id));
            resultSet.next();
            ticket = ticketFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        setConnection();
        List<Ticket> tickets = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_TICKETS);
            while (resultSet.next())
                tickets.add(ticketFactory(resultSet));
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
        return tickets;
    }

    @Override
    public void add(Ticket ticket) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertTicket(ticket));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    @Override
    public void remove(Ticket ticket) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteTicket(ticket));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}

    }

    @Override
    public void update(Ticket ticket) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateTicket(ticket));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    public List<Ticket> getTicketsByCustomer(Customer customer){
        setConnection();
        List<Ticket> tickets = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetTicketsByCustomer(customer));
            while (resultSet.next())
                tickets.add(ticketFactory(resultSet));
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
        return tickets;
    }

    /**
     * creating a ticket object from a db record
     * @param resultSet
     * @return ticket
     */
    private Ticket ticketFactory(ResultSet resultSet){
        Ticket ticket = null;
        try {
            ticket = new Ticket(
                    resultSet.getLong(0),
                    new Flight(
                            resultSet.getLong(1),
                            new AirlineCompany(
                                    resultSet.getLong(2),
                                    resultSet.getString(3),
                                    new Country(
                                            resultSet.getInt(4),
                                            resultSet.getString(5)
                                    ),
                                    new User(
                                            resultSet.getLong(6),
                                            resultSet.getString(7),
                                            resultSet.getString(8),
                                            resultSet.getString(9),
                                            new UserRole(
                                                    resultSet.getInt(10),
                                                    resultSet.getString(11)
                                            )
                                    )
                            ),
                            new Country(
                                    resultSet.getInt(12),
                                    resultSet.getString(13)
                            ),
                            new Country(
                                    resultSet.getInt(14),
                                    resultSet.getString(15)
                            ),
                            resultSet.getTimestamp(16),
                            resultSet.getTimestamp(17),
                            resultSet.getInt(18)
                    ),
                    new Customer(
                            resultSet.getLong(19),
                            resultSet.getString(20),
                            resultSet.getString(21),
                            resultSet.getString(22),
                            resultSet.getString(23),
                            resultSet.getString(24),
                            new User(
                                    resultSet.getLong(25),
                                    resultSet.getString(26),
                                    resultSet.getString(27),
                                    resultSet.getString(28),
                                    new UserRole(
                                            resultSet.getInt(29),
                                            resultSet.getString(30)
                                    )
                            )
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        return ticket;
    }
}

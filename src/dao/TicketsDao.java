package dao;

import models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
        }
        finally {closeConnection();}
        return tickets;
    }

    @Override
    public void add(Ticket ticket) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertTicket(ticket));
        }catch (Exception e){
            throw e;
        }
        finally {closeConnection();}
    }

    @Override
    public void remove(Ticket ticket) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteTicket(ticket));
        }catch (Exception e){
            throw e;
        }
        finally {closeConnection();}

    }

    @Override
    public void update(Ticket ticket) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateTicket(ticket));
        }catch (Exception e){
            throw e;
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
            System.err.println(e.getMessage());
        }
        finally {closeConnection();}
        return tickets;
    }

    /**
     * creating a ticket object from a db record
     * @param resultSet
     * @return ticket
     */
    private Ticket ticketFactory(ResultSet resultSet) throws SQLException {
        Ticket ticket = null;
        try {
                        ticket = new Ticket(
                    resultSet.getLong(1),
                    new Flight(
                            resultSet.getLong(2),
                            new AirlineCompany(
                                    resultSet.getLong(3),
                                    resultSet.getString(4),
                                    new Country(
                                            resultSet.getInt(5),
                                            resultSet.getString(6)
                                    ),
                                    new User(
                                            resultSet.getLong(7),
                                            resultSet.getString(8),
                                            resultSet.getString(9),
                                            resultSet.getString(10),
                                            new UserRole(
                                                    resultSet.getInt(11),
                                                    resultSet.getString(12)
                                            )
                                    )
                            ),
                            new Country(
                                    resultSet.getInt(13),
                                    resultSet.getString(14)
                            ),
                            new Country(
                                    resultSet.getInt(15),
                                    resultSet.getString(16)
                            ),
                            resultSet.getTimestamp(17),
                            resultSet.getTimestamp(18),
                            resultSet.getInt(19)
                    ),
                    new Customer(
                            resultSet.getLong(20),
                            resultSet.getString(21),
                            resultSet.getString(22),
                            resultSet.getString(23),
                            resultSet.getString(24),
                            resultSet.getString(25),
                            new User(
                                    resultSet.getLong(26),
                                    resultSet.getString(27),
                                    resultSet.getString(28),
                                    resultSet.getString(29),
                                    new UserRole(
                                            resultSet.getInt(30),
                                            resultSet.getString(31)
                                    )
                            )
                    )
            );
        }catch (Exception e){
            throw e;
        }
        return ticket;
    }
}

package business_logic;

import dao.CustomerDao;
import dao.TicketsDao;
import models.Customer;
import models.Ticket;

import java.util.List;

public class CustomerFacade extends AnonymousFacade {
    private LoginToken token;
    private CustomerDao customerDao = new CustomerDao();
    private TicketsDao ticketsDao = new TicketsDao();

    public CustomerFacade(LoginToken token) {
        this.token = token;
    }


    /**
     * @param customer
     * lets only this customer to update itself
     */
    public void updateCustomer(Customer customer){
        if(customer.user.id == token.id && token.role == Role.CUSTOMER)
            customerDao.update(customer);
    }


    /**
     * only this customer adds its own tickets,
     * the method checks if there are enough tickets on this flight
     * @param ticket
     *
     */
    public void addTicket(Ticket ticket){
        if(ticket.customer.user.id == token.id && token.role == Role.CUSTOMER){
            if(getFlightDao().get(ticket.flight.id).remainingTickets > 0)
                ticketsDao.add(ticket);
        }

    }

    /**
     * only this customer can remove its own tickets
     * @param ticket
     */
    public void removeTicket(Ticket ticket){
        if(ticket.customer.user.id == token.id && token.role == Role.CUSTOMER)
            ticketsDao.remove(ticket);
    }

    /**
     * @return List of tickets that this customer added
     */
    public List<Ticket> getMyTickets(){
        return ticketsDao.getTicketsByCustomer(customerDao.getCustomerByUsername(token.username));
    }


}

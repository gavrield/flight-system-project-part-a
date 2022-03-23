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

    public void updateCustomer(Customer customer){
        if(customer.user.id == token.id && token.role == Role.CUSTOMER)
            customerDao.update(customer);
    }
    public void addTicket(Ticket ticket){
        if(ticket.customer.user.id == token.id && token.role == Role.CUSTOMER)
            ticketsDao.add(ticket);
    }
    public void removeTicket(Ticket ticket){
        if(ticket.customer.user.id == token.id && token.role == Role.CUSTOMER)
            ticketsDao.remove(ticket);
    }
    public List<Ticket> getMyTickets(){
        return ticketsDao.getTicketsByCustomer(customerDao.getCustomerByUsername(token.username));
    }


}

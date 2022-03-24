package business_logic;

import dao.AdministratorDao;
import dao.AirlineCompanyDao;
import dao.CustomerDao;
import dao.TicketsDao;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class AdministratorFacade extends AnonymousFacade{

    private LoginToken token;
    private AdministratorDao administratorDao = new AdministratorDao();
    private AirlineCompanyDao airlineCompanyDao = new AirlineCompanyDao();
    private TicketsDao ticketsDao = new TicketsDao();

    public AdministratorFacade(LoginToken token){
        this.token = token;
    }

    public LoginToken getToken(){
        return this.token;
    }

    /**
     * list of all customers
     * @return List<Customer>
     */
    public List<Customer> getAllCustomers(){
        if(token.role == Role.ADMINISTRATOR)
            return getCustomerDao().getAll();
        return new ArrayList<Customer>();
    }

    /**
     * add user of type AirlineCompany
     * @param company
     */
    public void addAirlineCompany(AirlineCompany company){
        if(token.role == Role.ADMINISTRATOR){
            super.createNewUser(company.user);
            super.getAirlineCompanyDao().add(company);
        }

    }

    /**
     * add user of type Administrator
     * @param administrator
     */
    public void addAdministrator(Administrator administrator){
        if(token.role == Role.ADMINISTRATOR) {
            super.createNewUser(administrator.user);
            this.administratorDao.add(administrator);
        }
    }

    /**
     * remove user of type customer
     * @param customer
     */
    public void removeCustomer(Customer customer){
        if(token.role == Role.ADMINISTRATOR){
            super.getCustomerDao().remove(customer);
            super.getUserDao().remove(customer.user);
        }

    }

    /**
     * remove user of type AirlineCompany
     * @param company
     */
    public void removeAirlineCompany(AirlineCompany company){
        if(token.role == Role.ADMINISTRATOR) {
            this.airlineCompanyDao.remove(company);
            super.getUserDao().remove(company.user);
        }
    }

    /**
     * remove user of type Administrator
     * @param administrator
     */
    public void removeAdministrator(Administrator administrator){
        if(token.role == Role.ADMINISTRATOR) {
            this.administratorDao.remove(administrator);
            super.getUserDao().remove(administrator.user);
        }
    }


}

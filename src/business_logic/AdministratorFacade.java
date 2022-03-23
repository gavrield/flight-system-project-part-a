package business_logic;

import dao.AdministratorDao;
import dao.AirlineCompanyDao;
import dao.CustomerDao;
import models.Administrator;
import models.AirlineCompany;
import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class AdministratorFacade extends AnonymousFacade{

    private LoginToken token;
    private AdministratorDao administratorDao = new AdministratorDao();
    private AirlineCompanyDao airlineCompanyDao = new AirlineCompanyDao();

    public AdministratorFacade(LoginToken token){
        this.token = token;
    }

    public LoginToken getToken(){
        return this.token;
    }

    public List<Customer> getAllCustomers(){
        if(token.role == Role.ADMINISTRATOR)
            return getCustomerDao().getAll();
        return new ArrayList<Customer>();
    }

    public void addAirlineCompany(AirlineCompany company){
        if(token.role == Role.ADMINISTRATOR)
            super.getAirlineCompanyDao().add(company);
    }

    public void addAdministrator(Administrator administrator){
        if(token.role == Role.ADMINISTRATOR)
            this.administratorDao.add(administrator);
    }

    public void removeCustomer(Customer customer){
        if(token.role == Role.ADMINISTRATOR)
            super.getCustomerDao().remove(customer);
    }

    public void removeAirlineCompany(AirlineCompany company){
        if(token.role == Role.ADMINISTRATOR)
            this.airlineCompanyDao.remove(company);
    }

    public void removeAdministrator(Administrator administrator){
        if(token.role == Role.ADMINISTRATOR)
            this.administratorDao.remove(administrator);
    }


}

package business_logic;

import dao.CustomerDao;
import models.Customer;
import models.User;

import java.sql.SQLException;

public class AnonymousFacade extends BaseFacade {

    private CustomerDao customerDao = new CustomerDao();

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    /**
     * checks that the user is registered and returns her authorization token
     * @param username
     * @param password
     * @return LoginToken
     * @throws WrongPasswordError
     */
    public LoginToken login(String username, String password) throws WrongPasswordError {
        LoginToken token = null;

        User user = super.getUserDao().getUserByUsername(username);
        if (!(user != null && user.password.equals(password)))
            throw new WrongPasswordError();
        if (user.userRole.roleName.equalsIgnoreCase(String.valueOf(Role.ADMINISTRATOR)))
            token = new LoginToken(user.id, user.username, Role.ADMINISTRATOR);
        else if (user.userRole.roleName.equalsIgnoreCase(String.valueOf(Role.AIRLINE_COMPANY)))
            token = new LoginToken(user.id, username, Role.AIRLINE_COMPANY);
        else
            token = new LoginToken(user.id,user.username,Role.CUSTOMER);
        return token;
    }

    /**
     * letting anonymous user add itself as a customer user
     * @param customer
     */
    public void addCustomer(Customer customer){
        super.createNewUser(customer.user);
        try {
            getCustomerDao().add(customer);
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}

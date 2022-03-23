package business_logic;

import dao.CustomerDao;
import dao.UserDao;
import models.Customer;
import models.User;

public class AnonymousFacade extends BaseFacade {

    private CustomerDao customerDao = new CustomerDao();

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public LoginToken login(String username, String password) throws RuntimeException {
        LoginToken token = null;
        UserDao userDao = new UserDao();
        User user = userDao.getUserByUsername(username);
        if (!(user != null && user.password.equals(password)))
            throw new RuntimeException("password incorrect");

        if (user.userRole.roleName.equalsIgnoreCase(String.valueOf(Role.ADMINISTRATOR)))
            token = new LoginToken(user.id, user.username, Role.ADMINISTRATOR);
        else if (user.userRole.roleName.equalsIgnoreCase(String.valueOf(Role.AIRLINE_COMPANY)))
            token = new LoginToken(user.id, username, Role.AIRLINE_COMPANY);
        else
            token = new LoginToken(user.id,user.username,Role.CUSTOMER);
        return token;
    }

    public void addCustomer(Customer customer){
        super.createNewUser(customer.user);
        getCustomerDao().add(customer);
    }
}

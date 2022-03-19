package dal;

import models.Customer;
import models.User;
import models.UserRole;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends AbstractDao<Customer> {
    private String[] labels = {"customer_id", "first_name", "last_name",
            "address", "phone_no", "credit_card_no", "user_id", "username", "password", "email",
            "role_id", "role_name"
    };

    @Override
    public Customer get(int id) {
        setConnection();
        Customer customer = null;
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.sqlGetCustomerById(id));
            resultSet.next();
            customer = customerFactory(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
        return customer;
    }

    public Customer getCustomerByUsername(String username) {
        setConnection();
        Customer customer = null;
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.sqlGetCustomerByUsername(username));
            resultSet.next();
            customer = customerFactory(resultSet);
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
        return customer;
    }
    @Override
    public List<Customer> getAll() {
        setConnection();
        List<Customer> customers = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.SQL_GET_CUSTOMERS);
            while (resultSet.next()) {
                customers.add(customerFactory(resultSet));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
        return customers;
    }

    @Override
    public void add(Customer customer) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlInsertCustomer(customer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void remove(Customer customer) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlDeleteCustomer(customer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void update(Customer customer) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlUpdateCustomer(customer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    private Customer customerFactory(ResultSet resultSet){
        Customer customer = null;
        try {
            customer = new Customer(
                    resultSet.getLong(labels[0]),
                    resultSet.getString(labels[1]),
                    resultSet.getString(labels[2]),
                    resultSet.getString(labels[3]),
                    resultSet.getString(labels[4]),
                    resultSet.getString(labels[5]),
                    new User(
                            resultSet.getLong(labels[6]),
                            resultSet.getString(labels[7]),
                            resultSet.getString(labels[8]),
                            resultSet.getString(labels[9]),
                            new UserRole(
                                    resultSet.getInt(labels[10]),
                                    resultSet.getString(labels[11])
                            )
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
}

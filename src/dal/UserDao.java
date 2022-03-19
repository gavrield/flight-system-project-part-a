package dal;

import models.User;
import models.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User>{
    private String[] labels = {"user_id", "username", "password","email","role_id","role_name"};


    @Override
    public User get(int id) {
        setConnection();
        User user = null;
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.sqlGetUserById((long)id));
            resultSet.next();
            user = userFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return user;
    }

    @Override
    public List<User> getAll() {
        setConnection();
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.SQL_GET_USERS);
            while (resultSet.next()){
                users.add(userFactory(resultSet));
            }
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return users;
    }

    @Override
    public void add(User user) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlInsertUser(user));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void remove(User user) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlDeleteUser(user));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void update(User user) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlUpdateUser(user));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    public User getUserByUsername(String username) {
        setConnection();
        User user = null;
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlGetUserByUsername(username));
            resultSet.next();
            user = userFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return user;
    }
    private User userFactory(ResultSet resultSet){
        User user = null;
        try {
            user = new User(
                    resultSet.getLong(this.labels[0]),
                    resultSet.getString(this.labels[1]),
                    resultSet.getString(this.labels[2]),
                    resultSet.getString(this.labels[3]),
                    new UserRole(resultSet.getInt(this.labels[4]), resultSet.getString(this.labels[5]))
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}

package dao;

import models.User;
import models.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User, Long>{
    private final String[] labels = {"user_id", "username", "password","email","role_id","role_name"};


    @Override
    public User get(Long id) {
        setConnection();
        User user = null;
        try {
            ResultSet resultSet = statement.executeQuery(Queries.sqlGetUserById(id));
            resultSet.next();
            user = userFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
        return user;
    }

    @Override
    public List<User> getAll() {
        setConnection();
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_USERS);
            while (resultSet.next()){
                users.add(userFactory(resultSet));
            }
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
        return users;
    }

    @Override
    public void add(User user) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertUser(user));
        } catch (SQLException e) {
           throw  e;
        }
        finally {closeConnection();}
    }

    @Override
    public void remove(User user) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteUser(user));
        }catch (Exception e){
            throw e;
        }
        finally {closeConnection();}
    }

    @Override
    public void update(User user) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateUser(user));
        }catch (Exception e){
            throw e;
        }
        finally {closeConnection();}
    }

    public User getUserByUsername(String username) {
        setConnection();
        User user = null;
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetUserByUsername(username));
            resultSet.next();
            user = userFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
        return user;
    }

    /**
     * creating user object from a db record
     * @param resultSet
     * @return User
     */
    private User userFactory(ResultSet resultSet) throws SQLException{
        User user = null;
        try {
            user = new User(
                    resultSet.getLong(this.labels[0]),
                    resultSet.getString(this.labels[1]),
                    resultSet.getString(this.labels[2]),
                    resultSet.getString(this.labels[3]),
                    new UserRole(resultSet.getInt(this.labels[4]), resultSet.getString(this.labels[5]))
            );
        }catch (SQLException e){
            throw e;
        }
        return user;
    }
}

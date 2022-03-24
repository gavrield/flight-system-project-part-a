package dao;

import models.UserRole;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDao extends AbstractDao<UserRole, Integer>{
    private final String idLabel = "Id";
    private final String nameLabel = "Name";

    @Override
    public UserRole get(Integer id) {
        setConnection();
        UserRole userRole = null;
        try {
            ResultSet resultSet = statement.executeQuery(Queries.sqlGetUserRoleById(id));
            resultSet.next();
            userRole = new UserRole(
                    resultSet.getInt(this.idLabel), resultSet.getString(this.nameLabel));
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {closeConnection();}
        return userRole;
    }

    @Override
    public List<UserRole> getAll() {
        setConnection();
        List<UserRole> userRoles = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_USER_ROLES);
            while (resultSet.next()){
                userRoles.add(new UserRole(resultSet.getInt(this.idLabel), resultSet.getString(this.nameLabel)));
            }
            resultSet.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
        return userRoles;
    }

    @Override
    public void add(UserRole userRole) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertUserRole(userRole));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    @Override
    public void remove(UserRole userRole) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteUserRole(userRole));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
    }

    @Override
    public void update(UserRole userRole) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateUserRole(userRole));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {closeConnection();}
    }
}

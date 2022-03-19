package dal;

import models.UserRole;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDao extends AbstractDao<UserRole>{
    private final String idLabel = "Id";
    private final String nameLabel = "Name";

    @Override
    public UserRole get(int id) {
        setConnection();
        UserRole userRole = null;
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.sqlGetUserRoleById(id));
            resultSet.next();
            userRole = new UserRole(
                    resultSet.getInt(this.idLabel), resultSet.getString(this.nameLabel));
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
        return userRole;
    }

    @Override
    public List<UserRole> getAll() {
        setConnection();
        List<UserRole> userRoles = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.SQL_GET_USER_ROLES);
            while (resultSet.next()){
                userRoles.add(new UserRole(resultSet.getInt(this.idLabel), resultSet.getString(this.nameLabel)));
            }
            resultSet.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return userRoles;
    }

    @Override
    public void add(UserRole userRole) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlInsertUserRole(userRole));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void remove(UserRole userRole) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlDeleteUserRole(userRole));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void update(UserRole userRole) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlUpdateUserRole(userRole));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }
}

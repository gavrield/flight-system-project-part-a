package dal;

import models.Administrator;
import models.User;
import models.UserRole;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDao extends AbstractDao<Administrator>{
    @Override
    public Administrator get(int id) {
        setConnection();
        Administrator administrator = null;
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.sqlGetAdministratorById(id));
            resultSet.next();
            administrator = administratorFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return administrator;
    }

    @Override
    public List<Administrator> getAll() {
        setConnection();
        List<Administrator> administrators = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.SQL_GET_ADMINISTRATORS);
            while (resultSet.next())
                administrators.add(administratorFactory(resultSet));
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return null;
    }

    @Override
    public void add(Administrator administrator) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlInsertAdministrator(administrator));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void remove(Administrator administrator) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlDeleteAdministrator(administrator));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void update(Administrator administrator) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlUpdateAdministrator(administrator));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    private Administrator administratorFactory(ResultSet resultSet){
        Administrator administrator = null;
        try {
            administrator = new Administrator(
                    resultSet.getInt(0),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    new User(
                            resultSet.getLong(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            new UserRole(
                                    resultSet.getInt(7),
                                    resultSet.getString(8)
                            )
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        return administrator;
    }
}

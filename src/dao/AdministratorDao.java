package dao;

import models.Administrator;
import models.User;
import models.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDao extends AbstractDao<Administrator, Integer> {
    @Override
    public Administrator get(Integer id) {
        setConnection();
        Administrator administrator = null;
        try {
            ResultSet resultSet = statement.executeQuery(Queries.sqlGetAdministratorById(id));
            resultSet.next();
            administrator = administratorFactory(resultSet);
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return administrator;
    }

    @Override
    public List<Administrator> getAll() {
        setConnection();
        List<Administrator> administrators = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_ADMINISTRATORS);
            while (resultSet.next())
                administrators.add(administratorFactory(resultSet));
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return administrators;
    }

    @Override
    public void add(Administrator administrator) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertAdministrator(administrator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }

    @Override
    public void remove(Administrator administrator) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteAdministrator(administrator));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }

    @Override
    public void update(Administrator administrator) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateAdministrator(administrator));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
    }

    /**
     * creating administrator POCO from a database record
     * @param resultSet
     * @return administrator POCO
     */
    private Administrator administratorFactory(ResultSet resultSet) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return administrator;
    }
}

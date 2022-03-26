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
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return administrators;
    }

    @Override
    public void add(Administrator administrator) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertAdministrator(administrator));
        } catch (Exception e) {
            throw e;
        }
        finally {
            closeConnection();
        }
    }

    @Override
    public void remove(Administrator administrator) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteAdministrator(administrator));
        } catch (Exception e) {
            throw e;
        }
        finally {
            closeConnection();
        }
    }

    @Override
    public void update(Administrator administrator) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateAdministrator(administrator));
        } catch (SQLException e) {
            throw e;
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
    private Administrator administratorFactory(ResultSet resultSet) throws SQLException{
        Administrator administrator = null;
        try {
                        administrator = new Administrator(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    new User(
                            resultSet.getLong(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            new UserRole(
                                    resultSet.getInt(8),
                                    resultSet.getString(9)
                            )
                    )
            );
        } catch (Exception e) {
            throw e;
        }
        return administrator;
    }
}

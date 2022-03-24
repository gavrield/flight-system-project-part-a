package dao;

import models.AirlineCompany;
import models.Country;
import models.User;
import models.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompanyDao extends AbstractDao<AirlineCompany, Long>{
    @Override
    public AirlineCompany get(Long id) {
        setConnection();
        AirlineCompany airlineCompany = null;
        try {
            ResultSet resultSet = statement.executeQuery(Queries.sqlGetAirlineById(id));
            resultSet.next();
            airlineCompany = airlineCompanyFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return airlineCompany;
    }

    @Override
    public List<AirlineCompany> getAll() {
        setConnection();
        List<AirlineCompany> airlineCompanies = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_AIRLINE_COMPANIES);
            while (resultSet.next()){
                airlineCompanies.add(airlineCompanyFactory(resultSet));
            }
            resultSet.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return airlineCompanies;
    }

    @Override
    public void add(AirlineCompany airlineCompany) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertAirlineCompany(airlineCompany));
        }catch (Exception e){
            throw e;
        }
        finally {
            closeConnection();
        }
    }

    @Override
    public void remove(AirlineCompany airlineCompany) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteAirlineCompany(airlineCompany));
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }

    @Override
    public void update(AirlineCompany airlineCompany) throws SQLException {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateAirlineCompany(airlineCompany));
        }catch (Exception e){
            throw e;
        }
        finally {
            closeConnection();
        }
    }

    /**
     * returning a list of airlineCompanies POCO from a specific country
     * @param country_id
     * @return list of airlineCompany POCO
     */
    public List<AirlineCompany> getAirlinesByCountry(int country_id){
        setConnection();
        List<AirlineCompany> airlineCompanies = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetAirlinesByCountry(country_id));
            while (resultSet.next()){
                airlineCompanies.add(airlineCompanyFactory(resultSet));
            }
            resultSet.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return airlineCompanies;
    }

    /**
     * returning an airlineCompany POCO by the username
     * @param username
     * @return airlineCompany POCO
     */
    public AirlineCompany getAirlinesByUsername(String username){
        setConnection();
        AirlineCompany airlineCompany = null;
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetAirlineCompanyByUsername(username));
            resultSet.next();
            airlineCompany = airlineCompanyFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return airlineCompany;
    }

    /**
     * creating a airlineCompany POCO from a database record
     * @param resultSet
     * @return airlineCompany POCO
     */
    private AirlineCompany airlineCompanyFactory(ResultSet resultSet){
        AirlineCompany airlineCompany =null;
        try {
            airlineCompany = new AirlineCompany(
                    resultSet.getLong(0),
                    resultSet.getString(1),
                    new Country(
                            resultSet.getInt(2),
                            resultSet.getString(3)
                    ),
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return airlineCompany;
    }
}

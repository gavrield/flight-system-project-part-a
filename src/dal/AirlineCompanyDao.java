package dal;

import models.AirlineCompany;
import models.Country;
import models.User;
import models.UserRole;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompanyDao extends AbstractDao<AirlineCompany>{
    @Override
    public AirlineCompany get(int id) {
        setConnection();
        AirlineCompany airlineCompany = null;
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.sqlGetAirlineById((long)id));
            resultSet.next();
            airlineCompany = airlineCompanyFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return airlineCompany;
    }

    @Override
    public List<AirlineCompany> getAll() {
        setConnection();
        List<AirlineCompany> airlineCompanies = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.SQL_GET_AIRLINE_COMPANIES);
            while (resultSet.next()){
                airlineCompanies.add(airlineCompanyFactory(resultSet));
            }
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return airlineCompanies;
    }

    @Override
    public void add(AirlineCompany airlineCompany) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlInsertAirlineCompany(airlineCompany));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void remove(AirlineCompany airlineCompany) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlDeleteAirlineCompany(airlineCompany));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void update(AirlineCompany airlineCompany) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlUpdateAirlineCompany(airlineCompany));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }


    public List<AirlineCompany> getAirlinesByCountry(int country_id){
        setConnection();
        List<AirlineCompany> airlineCompanies = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlGetAirlinesByCountry(country_id));
            while (resultSet.next()){
                airlineCompanies.add(airlineCompanyFactory(resultSet));
            }
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return airlineCompanies;
    }

    public AirlineCompany getAirlinesByUsername(String username){
        setConnection();
        AirlineCompany airlineCompany = null;
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlGetAirlineCompanyByUsername(username));
            resultSet.next();
            airlineCompany = airlineCompanyFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return airlineCompany;
    }
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

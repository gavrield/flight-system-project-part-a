package dao;

import models.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDao extends AbstractDao<Country, Integer>{

    private final String idLabel = "Id";
    private final String nameLabel = "Name";

    @Override
    public Country get(Integer id){
        setConnection();
        Country country = null;
        try {
            ResultSet resultSet = super.statement.executeQuery(Queries.sqlGetCountryById(id));
            resultSet.next();
            country = new Country(
                    resultSet.getInt(this.idLabel), resultSet.getString(this.nameLabel));
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return country;
    }

    @Override
    public List<Country> getAll() {
        setConnection();
        List<Country> countries = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_COUNTRIES);
            while (resultSet.next()){
                countries.add(new Country(resultSet.getInt(this.idLabel), resultSet.getString(this.nameLabel)));
            }
            resultSet.close();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        closeConnection();
        return countries;
    }

    @Override
    public void add(Country country) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertCountry(country));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        closeConnection();
    }

    @Override
    public void remove(Country country) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteCountry(country));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        closeConnection();
    }

    @Override
    public void update(Country country) {
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateCountry(country));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        closeConnection();
    }
}

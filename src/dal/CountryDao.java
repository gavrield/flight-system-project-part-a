package dal;

import models.Country;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDao extends AbstractDao<Country>{

    private final String idLabel = "Id";
    private final String nameLabel = "Name";

    @Override
    public Country get(int id) {
        setConnection();
        Country country = null;
        try {
            ResultSet resultSet = super.statement.executeQuery(ConstantsAndQueries.sqlGetCountryById(id));
            resultSet.next();
            country = new Country(
                    resultSet.getInt(this.idLabel), resultSet.getString(this.nameLabel));
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeConnection();
        return country;
    }

    @Override
    public List<Country> getAll() {
        setConnection();
        List<Country> countries = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.SQL_GET_COUNTRIES);
            while (resultSet.next()){
                countries.add(new Country(resultSet.getInt(this.idLabel), resultSet.getString(this.nameLabel)));
            }
            resultSet.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return countries;
    }

    @Override
    public void add(Country country) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlInsertCountry(country));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void remove(Country country) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlDeleteCountry(country));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void update(Country country) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlUpdateCountry(country));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }
}

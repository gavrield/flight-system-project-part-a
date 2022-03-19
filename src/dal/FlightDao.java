package dal;

import models.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FlightDao extends AbstractDao<Flight>{

    private String[] labels ={
            "flight_id","airline_id","airline_name","country_id","country_name",
            "user_id","username","password","email","role_id",
            "role_name","origin_country_id","origin_country_name",
            "destination_country_id","destination_country_name",
            "departure_time","landing_time","remaining_tickets"};


    @Override
    public Flight get(int id) {
        setConnection();
        Flight flight = null;
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.sqlGetFlightById((long)id));
            resultSet.next();
            flight = flightFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flight;
    }

    @Override
    public List<Flight> getAll() {
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(ConstantsAndQueries.SQL_GET_FLIGHTS);
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flights;
    }

    @Override
    public void add(Flight flight) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlInsertFlight(flight));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void remove(Flight flight) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlDeleteFlight(flight));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void update(Flight flight) {
        setConnection();
        try {
            statement.executeUpdate(ConstantsAndQueries.sqlUpdateFlight(flight));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
    }

    public List<Flight> getFlightsByParameters(int originCountryId, int destCountryId, Date date){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlGetFlightsByParameters
                            (originCountryId, destCountryId,date.toString()));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flights;
    }
    public List<Flight> getFlightsByOriginCountryId(int country_id){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlGetFlightsByParameters
                            (country_id, 0, ""));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flights;
    }
    public List<Flight> getFlightsByDestinationCountryId(int country_id){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlGetFlightsByParameters
                            (0, country_id, ""));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flights;
    }
    public List<Flight>  getFlightsByDepartureDate(Date date){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlGetFlightsByParameters
                            (0, 0, date.toString()));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flights;
    }

    public List<Flight> getFlightsByLandingDate(Date date){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlGetFlightsByLandingDate(date));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flights;
    }

    public List<Flight> getDepartureFlights(int country_id){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlDepartureFlights(country_id));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flights;
    }

    public List<Flight> getArrivalFlights(int country_id){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (ConstantsAndQueries.sqlArrivalFlights(country_id));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return flights;
    }

    private Flight flightFactory(ResultSet resultSet){
        Flight flight=null;
        try {
            flight = new Flight(
                    resultSet.getLong(this.labels[0]),
                    new AirlineCompany(
                            resultSet.getLong(this.labels[1]),
                            resultSet.getString(labels[2]),
                            new Country(
                                    resultSet.getInt(labels[3]),
                                    resultSet.getString(labels[4])
                            ),
                            new User(
                                    resultSet.getLong(labels[5]),
                                    resultSet.getString(labels[6]),
                                    resultSet.getString(labels[7]),
                                    resultSet.getString(labels[8]),
                                    new UserRole(
                                            resultSet.getInt(labels[9]),
                                            resultSet.getString(labels[10])
                                    )
                            )
                    ),
                    new Country(
                            resultSet.getInt(labels[11]),
                            resultSet.getString(labels[12])
                    ),
                    new Country(
                            resultSet.getInt(labels[13]),
                            resultSet.getString(labels[14])
                    ),
                    resultSet.getTimestamp(labels[15]),
                    resultSet.getTimestamp(labels[16]),
                    resultSet.getInt(labels[17])
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        return flight;
    }
}

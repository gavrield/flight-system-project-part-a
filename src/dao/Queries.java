package dao;

import models.*;

import java.sql.Date;

/**
 * contains static sql queries
 */
public class Queries {


    /*
     * static final sql queries
     */
    public final static String SQL_GET_FLIGHTS = "SELECT * FROM get_flights();";
    public final static String SQL_GET_USERS = "SELECT * FROM get_users();";
    public final static String SQL_GET_USER_ROLES = "SELECT * FROM \"User_Roles\";";
    public final static String SQL_GET_AIRLINE_COMPANIES = "SELECT * FROM get_airline_companies();";
    public final static String SQL_GET_ADMINISTRATORS = "SELECT * FROM get_administrators();";
    public final static String SQL_GET_CUSTOMERS = "SELECT * FROM get_customers();";
    public final static String SQL_GET_COUNTRIES = "SELECT * FROM \"Countries\";";
    public final static String SQL_GET_TICKETS = "SELECT * FROM get_tickets();";


    /*
     * sql queries strings methods
     */

    /* User Role table queries*/
    public static String sqlGetUserRoleById(int id) {
        return "SELECT * FROM \"User_Roles\" WHERE \"User_Roles\".\"Id\" = " + id + ";";
    }

    public static String sqlInsertUserRole(UserRole userRole) {
        return "INSERT INTO \"User_Roles\" (\"Name\") VALUES ('" + userRole.roleName + "');";
    }

    public static String sqlDeleteUserRole(UserRole userRole) {
        return "DELETE FROM \"User_Roles\" WHERE \"User_Roles\".\"Id\" = " + userRole.id + ";";
    }

    public static String sqlUpdateUserRole(UserRole userRole) {
        return "UPDATE \"User_Roles\" SET \"Name\"= '" + userRole.roleName +
                "' WHERE \"User_Roles\".\"Id\" = " + userRole.id + ";";
    }

    /* User table queries */
    public static String sqlGetUserById(Long id) {
        return "SELECT * FROM get_users() AS \"users\" WHERE \"users\".\"user_id\" = " + id + ";";
    }

    public static String sqlInsertUser(User user) {
        return String.format("INSERT INTO \"Users\" " +
                        "(\"Username\",\"Password\",\"Email\",\"User_Role\") " +
                        "VALUES ('%s','%s','%s',%d);",
                user.username, user.password, user.email, user.userRole.id);
    }

    public static String sqlDeleteUser(User user) {
        return "DELETE FROM \"Users\" WHERE \"Users\".\"Id\" = " + user.id + ";";
    }

    public static String sqlUpdateUser(User user) {
        return String.format("UPDATE \"Users\"" +
                        "SET \"Username\" = '%s', \"Password\" = '%s', " +
                        "\"Email\" = '%s', \"User_Role\" = %d",
                user.username, user.password, user.email, user.userRole.id);
    }

    public static String sqlGetUserByUsername(String username) {
        return "SELECT * FROM get_user_by_username(" + username + ");";
    }

    /* Flight table sql queries  */
    public static String sqlGetFlightById(Long id) {
        return "SELECT * FROM get_flights() AS \"flights\" WHERE \"flights\".\"flight_id\" = " + id + ";";
    }

    public static String sqlInsertFlight(Flight flight) {
        return String.format("INSERT INTO \"Flights\"(\"Airline_Company_Id\", \"Origin_Country_Id\"," +
                        "\"Destination_Country_Id\", \"Departure_Time\", \"Landing_Time\", \"Remaining_Tickets\")" +
                        "VALUES (%d, %d, %d,'%s','%s',%d);",
                flight.airlineCompany.id, flight.originCountry.id, flight.destinationCountry.id,
                flight.departureTime.toString(), flight.landingTime.toString(), flight.remainingTickets);
    }

    public static String sqlDeleteFlight(Flight flight) {
        return "DELETE FROM \"Flights\" WHERE \"Flights\".\"Id\" = " + flight.id + ";";
    }

    public static String sqlUpdateFlight(Flight flight) {
        return String.format("UPDATE \"Flights\" SET \"Airline_Company_Id\" = %d, \"Origin_Country_Id\" = %d," +
                        "\"Destination_Country_Id\" = %d, \"Departure_Time\" = '%s', \"Landing_Time\" ='%s'," +
                        "\"Remaining_Tickets\" = %d WHERE \"Flights\".\"Id\" = %d;",
                flight.airlineCompany.id, flight.originCountry.id,
                flight.destinationCountry.id, flight.departureTime.toString(),
                flight.landingTime.toString(), flight.remainingTickets, flight.id);
    }

    public static String sqlGetFlightsByAirlineId(long id) {
        return "SELECT * FROM get_flights_by_airline_id(" + id + ");";
    }

    public static String sqlDepartureFlights(int countryId) {
        return "SELECT * FROM get_departure_flights(" + countryId + ");";
    }

    public static String sqlArrivalFlights(int countryId) {
        return "SELECT * FROM get_arrival_flights(" + countryId + ");";
    }

    public static String sqlGetFlightsByParameters(int originCountryId, int destCountryId, String date) {
        return String.format("SELECT * FROM get_flights_by_parameters(%d, %d, '%s')",
                originCountryId, destCountryId, date);
    }

    public static String sqlGetFlightsByLandingDate(Date date) {
        return "SELECT * FROM get_flights() AS \"flights\" WHERE DATE(\"flights\".\"landing_time\") = "
                + date.toString() + ";";
    }


    /* Country table queries*/
    public static String sqlGetCountryById(int id) {
        return "SELECT * FROM \"Countries\" WHERE \"Countries\".\"Id\" =" + id + ";";
    }

    public static String sqlInsertCountry(Country country) {
        return "INSERT INTO \"Countries\" (\"Name\") VALUES ('" + country.name + "');";
    }

    public static String sqlDeleteCountry(Country country) {
        return "DELETE FROM \"Countries\" WHERE \"Countries\".\"Id\" = " + country.id + ";";
    }

    public static String sqlUpdateCountry(Country country) {
        return "UPDATE \"Countries\" SET \"Name\"= '" + country.name +
                "' WHERE \"Countries\".\"Id\" = " + country.id + ";";
    }

    /* Customers table queries*/
    public static String sqlGetCustomerById(Long id) {
        return "SELECT * FROM get_customers() AS \"customers\" WHERE \"customers\".\"customer_id \" = "
                + id + ";";
    }

    public static String sqlGetCustomerByUsername(String username) {
        return "SELECT * FROM get_customer_by_username(" + username + ");";
    }

    public static String sqlInsertCustomer(Customer customer) {
        return String.format("INSERT INTO \"Customers\"(\"First_Name\",\"Last_Name\",\"Address\"," +
                        "\"Phone_No\", \"Credit_Card_No\", \"User_Id\") VALUES('%s','%s','%s','%s','%s',%d);",
                customer.firstName, customer.lastName, customer.address, customer.phoneNo, customer.creditCardNo,
                customer.user.id);
    }

    public static String sqlDeleteCustomer(Customer customer) {
        return "DELETE FROM \"Customers\" WHERE \"Customers\".\"Id\" = " + customer.id + ";";
    }

    public static String sqlUpdateCustomer(Customer customer) {
        return String.format("UPDATE \"Customers\" SET \"First_Name\" = '%s', \"Last_Name\" = '%s'," +
                        "\"Address\" = '%s', \"Phone_No\" = '%s', \"Credit_Card_No\" = '%s, \"User_Id\" = '%s'" +
                        "WHERE \"Customers\".\"Id\" = %d;",
                customer.firstName, customer.lastName, customer.address, customer.phoneNo, customer.creditCardNo,
                customer.user.id, customer.id);
    }

    /* Tickets table queries*/
    public static String sqlGetTicketsByCustomer(Customer customer) {
        return "SELECT * FROM get_tickets_by_customer(" + customer.id + ");";
    }

    public static String sqlGetTicketById(Long id) {
        return "SELECT * FROM get_tickets AS \"tickets\" WHERE \"tickets\".\"ticket_id\" = " + id + ";";
    }

    /**
     * generates sql request that calls the add_ticket stored procedure
     * @param ticket
     * @return String
     */
    public static String sqlInsertTicket(Ticket ticket) {
        return "CALL add_ticket(" + ticket.flight.id + "," + ticket.customer.id + ");";
    }

    /**
     * generates sql request that calls the remove_ticket stored procedure
     * @param ticket
     * @return String
     */
    public static String sqlDeleteTicket(Ticket ticket) {
        return "CALL remove_ticket(" + ticket.flight.id + "," + ticket.id + ");";
    }

    public static String sqlUpdateTicket(Ticket ticket) {
        return String.format("UPDATE \"Tickets\" SET \"Flight_Id\" = %d, \"Customer_Id\" = %d" +
                        "WHERE \"Tickets\".\"Id\" = %d",
                ticket.flight.id, ticket.customer.id, ticket.id);
    }

    /* Airline_Companies table queries*/
    public static String sqlGetAirlineById(Long id) {
        return "SELECT * FROM get_airline_companies() AS \"airlines\" WHERE \"airlines\".\"airline_id\" =" + id + ";";
    }

    public static String sqlInsertAirlineCompany(AirlineCompany airlineCompany) {
        return String.format(
                "INSERT INTO \"Airline_Companies\" (\"Name\", \"Country_Id\", \"User_Id\")" +
                        "VALUES ('%s', %d, %d);",
                airlineCompany.name, airlineCompany.country.id, airlineCompany.user.id
        );
    }

    public static String sqlDeleteAirlineCompany(AirlineCompany airlineCompany) {
        return "DELETE FROM \"Airline_Companies\" WHERE \"Airline_Companies\".\"Id\" = " + airlineCompany.id + ";";
    }

    public static String sqlUpdateAirlineCompany(AirlineCompany airlineCompany) {
        return String.format("UPDATE \"Airline_Companies\" SET \"Name\" = '%s', \"Country_Id\" = %d," +
                        "\"User_Id\" = %d WHERE \"Airline_Companies\".\"Id\" = %d);",
                airlineCompany.name, airlineCompany.country.id, airlineCompany.user.id, airlineCompany.id);
    }

    public static String sqlGetAirlinesByCountry(int countryId) {
        return "SELECT * FROM get_airline_companies() AS \"airlines\" WHERE \"airlines\".\"country_id\" ="
                + countryId + ";";
    }

    public static String sqlGetAirlineCompanyByUsername(String username) {
        return "SELECT * FROM get_airline_by_username(" + username + ");";
    }

    /* Administrators table queries*/
    public static String sqlGetAdministratorById(int id) {
        return "SELECT * FROM get_administrators() AS \"admins\" WHERE \"admins\".\"admin_id\" =" + id + ";";
    }

    public static String sqlInsertAdministrator(Administrator administrator) {
        return String.format(
                "INSERT INTO \"Administrators\" (\"First_Name\", \"Last_Name\", \"User_Id\")" +
                        "VALUES ('%s', '%s', %d);",
                administrator.firstName, administrator.lastName, administrator.user.id
        );
    }

    public static String sqlDeleteAdministrator(Administrator administrator) {
        return "DELETE FROM \"Administrators\" WHERE \"Id\" = " + administrator.id + ";";
    }

    public static String sqlUpdateAdministrator(Administrator administrator) {
        return String.format(
                "UPDATE \"Administrators\" SET \"First_Name\" = '%s', \"Last_Name\" = '%s', \"User_Id\" = %d" +
                        "WHERE \"Administrators\".\"Id\" = %d;",
                administrator.firstName, administrator.lastName, administrator.user.id, administrator.id
        );
    }








}

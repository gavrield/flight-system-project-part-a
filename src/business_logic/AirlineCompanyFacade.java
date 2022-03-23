package business_logic;

import models.AirlineCompany;
import models.Flight;
import models.User;

import java.util.List;

public class AirlineCompanyFacade extends AnonymousFacade{
    private LoginToken token;

    public AirlineCompanyFacade(LoginToken token){
        this.token = token;
    }

    public LoginToken getToken(){
        return this.token;
    }

    public void updateAirline(AirlineCompany airlineCompany){
        if(airlineCompany.user.id == token.id && token.role == Role.AIRLINE_COMPANY)
            getAirlineCompanyDao().update(airlineCompany);
    }
    public List<Flight> getMyFlights(){
        AirlineCompany airlineCompany = super.getAirlineCompanyDao().getAirlinesByUsername(token.username);
        return super.getFlightDao().getFlightsByAirlineId(airlineCompany.id);
    }
    public void addFlight(Flight flight){
        if(flightValidator(flight) && token.role == Role.AIRLINE_COMPANY)
            getFlightDao().add(flight);
    }
    public void updateFlight(Flight flight){
        if(flightValidator(flight)&& token.role == Role.AIRLINE_COMPANY)
            getFlightDao().update(flight);
    }
    public void removeFlight(Flight flight){
        if(flightValidator(flight)&& token.role == Role.AIRLINE_COMPANY)
            getFlightDao().remove(flight);
    }

    /**
     * checks if a flight is concise with the business logic:
     * 1) landing time is after departure time
     * 2) the remainingTickets field is positive
     * 3) the destination country is different from the origin country
     * 4) the airline company is the same as this company
     */
    private boolean flightValidator(Flight flight){
        if(flight.departureTime.after(flight.landingTime))
            return false;
        if (flight.remainingTickets < 0)
            return false;
        if(flight.destinationCountry.id == flight.originCountry.id)
            return false;
        if (flight.airlineCompany.user.id != token.id)
            return false;
        return true;
    }
}

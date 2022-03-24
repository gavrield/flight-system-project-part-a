package models;

import java.sql.Timestamp;

public class Flight implements POCO{

    public Long id;
    public AirlineCompany airlineCompany;
    public Country originCountry;
    public Country destinationCountry;
    public Timestamp departureTime;
    public Timestamp landingTime;
    public Integer remainingTickets;

    public Flight(Long id, AirlineCompany airlineCompany, Country originCountry,
                  Country destinationCountry, Timestamp departureTime, Timestamp landingTime,
                  Integer remainingTickets) {
        this.id = id;
        this.airlineCompany = airlineCompany;
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.remainingTickets = remainingTickets;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineCompany=" + airlineCompany +
                ", originCountry=" + originCountry +
                ", destinationCountry=" + destinationCountry +
                ", departureTime=" + departureTime +
                ", landingTime=" + landingTime +
                ", remainingTickets=" + remainingTickets +
                '}';
    }
}

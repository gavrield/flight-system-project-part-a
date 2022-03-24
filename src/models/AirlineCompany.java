package models;

public class AirlineCompany implements POCO{

    public Long id;
    public String name;
    public Country country;
    public User user;

    public AirlineCompany(Long id, String name, Country country, User user) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.user = user;
    }

    @Override
    public String toString() {
        return "AirlineCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", user=" + user +
                '}';
    }
}

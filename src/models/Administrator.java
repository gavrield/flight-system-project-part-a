package models;

public class Administrator implements POCO{
    public Integer id;
    public String firstName;
    public String lastName;
    public User user;

    public Administrator(Integer id, String firstName, String lastName, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", user=" + user +
                '}';
    }
}

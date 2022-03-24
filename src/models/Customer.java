package models;

public class Customer implements POCO{

    public Long id;
    public String firstName;
    public String lastName;
    public String address;
    public String phoneNo;
    public String creditCardNo;
    public User user;

    public Customer
            (Long id, String firstName, String lastName,
             String address, String phoneNo, String creditCardNo, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNo = phoneNo;
        this.creditCardNo = creditCardNo;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", creditCardNo='" + creditCardNo + '\'' +
                ", user=" + user +
                '}';
    }
}

package models;

public class User implements POCO{
    public Long id;
    public String username;
    public String password;
    public String email;
    public UserRole userRole;

    public User(Long id, String username, String password, String email, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}

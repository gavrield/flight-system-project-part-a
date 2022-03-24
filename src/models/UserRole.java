package models;

public class UserRole implements POCO{
    public Integer id;
    public String roleName;

    public UserRole(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

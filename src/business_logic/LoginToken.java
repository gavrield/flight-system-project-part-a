package business_logic;

public class LoginToken {
    public Long id;
    public String username;
    public Role role;

    public LoginToken(Long id, String name, Role role) {
        this.id = id;
        this.username = name;
        this.role = role;
    }
}

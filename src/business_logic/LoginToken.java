package business_logic;

import models.POCO;

/**
 * a POCO token
 */
public class LoginToken implements POCO {
    public Long id;
    public String username;
    public Role role;

    public LoginToken(Long id, String name, Role role) {
        this.id = id;
        this.username = name;
        this.role = role;
    }
}

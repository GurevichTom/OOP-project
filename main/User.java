package main;

/**
 * The User interface represents a user in the system, which can be a buyer or a seller.
 */
public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


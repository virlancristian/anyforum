package net.anyforum.backend.models.user;

public class UserLogin extends BasicUser {
    private String password;

    public UserLogin() {
        super();
    }

    public UserLogin(String username, String email, String password) {
        super(username, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

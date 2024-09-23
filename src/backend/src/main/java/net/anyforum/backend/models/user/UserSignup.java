package net.anyforum.backend.models.user;

public class UserSignup extends BasicUser {
    private String password;
    private String repeatedPassword;

    public UserSignup() {
        super();
    }

    public UserSignup(String username, String email, String password, String repeatedPassword) {
        super(username, email);
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}

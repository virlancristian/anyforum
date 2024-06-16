package net.anyforum.backend.constants;

public class AuthConstants {
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{1,50}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$";
    public static final String ID_REGEX = "^[1-9][0-9]{0,99}$";
    public static final String AUTH_TOKEN_REGEX = "^[a-zA-Z0-9]{50,100}$";
}

package com.ecommerce.orders.domain.constants;

public class AuthErrorMessages {

    public static final String USER_REQUIRED = "Username is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String EMAIL_ERROR = "Invalid email address.";
    public static final String PASSWORD_ERROR = "The password must be at least 6 characters long.";
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String INVALID_PASSWORD = "Invalid password";
    public static final String USERNAME_NOT_EXIST = "Username does not exist";
    public static final String ROLE_NOT_EXIST = "Role does not exist";

    public static final String ROLE_AUTH = "ROLE_";
    public static final String LOGGED_IN = "User loged successfuly";
    public static final Boolean LOGGED_IN_SUCCESS = true;

    private AuthErrorMessages() {
    }
}

package com.example.backend.model; // eller dto om du har en sådan mapp

public class LoginResponse {

    public enum LoginResult {
        SUCCESS,
        WRONG_PASSWORD,
        USER_NOT_FOUND
    }

    private LoginResult result;
    private User user; // valfritt – du kan också bara returnera username/id om du vill

    public LoginResponse(LoginResult result, User user) {
        this.result = result;
        this.user = user;
    }

    public LoginResult getResult() {
        return result;
    }

    public User getUser() {
        return user;
    }
}

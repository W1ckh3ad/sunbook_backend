package de.sunbook.api.models;

public class AuthenticationResponseModel {
    private final String jwt;

    public AuthenticationResponseModel(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}

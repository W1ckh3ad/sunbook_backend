package de.sunbook.api.models.responsemodels;

/*
This class defines the response for the Authentification of an user
*/
public class AuthenticationResponseModel {
    private final String jwt;

    public AuthenticationResponseModel(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}

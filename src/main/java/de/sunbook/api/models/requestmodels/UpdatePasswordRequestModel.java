package de.sunbook.api.models.requestmodels;

/*
This class defines the request model for the password change
*/
public class UpdatePasswordRequestModel {
    private String password;

    public UpdatePasswordRequestModel() {
    }

    public UpdatePasswordRequestModel(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

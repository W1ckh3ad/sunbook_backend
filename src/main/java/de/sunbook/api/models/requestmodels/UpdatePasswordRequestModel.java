package de.sunbook.api.models.requestmodels;

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

package de.sunbook.api.models.requestmodels;

/*
This class defines the request model for a role change of an user
*/
public class UpdateRoleRequestModel {
    private String role;

    public UpdateRoleRequestModel() {
    }

    public UpdateRoleRequestModel(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

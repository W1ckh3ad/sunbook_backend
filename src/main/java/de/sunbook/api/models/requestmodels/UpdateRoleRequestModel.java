package de.sunbook.api.models.requestmodels;

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

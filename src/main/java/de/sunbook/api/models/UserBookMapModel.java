package de.sunbook.api.models;

public class UserBookMapModel {
    private Integer uid;
    private Integer userId;
    private String description;

    public UserBookMapModel() {
    }

    public UserBookMapModel(Integer uid, Integer userId, String description) {
        this.uid = uid;
        this.userId = userId;
        this.description = description;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

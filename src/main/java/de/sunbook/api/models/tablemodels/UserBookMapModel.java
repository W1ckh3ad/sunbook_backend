package de.sunbook.api.models.tablemodels;

public class UserBookMapModel {
    private Integer uid;
    private Integer userId;
    private String userDescription;

    public UserBookMapModel() {
    }

    public UserBookMapModel(Integer uid, Integer userId, String description) {
        this.uid = uid;
        this.userId = userId;
        this.userDescription = description;
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
        return userDescription;
    }

    public void setDescription(String description) {
        this.userDescription = description;
    }
}

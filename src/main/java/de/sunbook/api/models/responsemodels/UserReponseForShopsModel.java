package de.sunbook.api.models.responsemodels;

/*
This class defines the response for shops
*/
public class UserReponseForShopsModel {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String houseNum;
    private String plz;
    private String role;
    private String city;
    private String shopName;

    public UserReponseForShopsModel() {
    }

    public UserReponseForShopsModel(Integer userId, String firstName, String lastName, String email, String street,
            String houseNum, String plz, String role, String city, String shopName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.houseNum = houseNum;
        this.plz = plz;
        this.role = role;
        this.city = city;
        this.shopName = shopName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

}

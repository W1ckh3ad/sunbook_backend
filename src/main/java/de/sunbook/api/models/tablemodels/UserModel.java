package de.sunbook.api.models.tablemodels;

public class UserModel {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String houseNum;
    private String plz;
    private String role;
    private String city;
    private String password;
    private String favPayment;
    private Boolean isActive;
    private String shopName;

    public UserModel() {
    }

    public UserModel(Integer userId, String firstName, String lastName, String email, String street, String houseNum,
            String plz, String role, String city, String password, String favPayment, boolean isActive,
            String shopName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.houseNum = houseNum;
        this.plz = plz;
        this.role = role;
        this.city = city;
        this.password = password;
        this.favPayment = favPayment;
        this.isActive = isActive;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavPayment() {
        return favPayment;
    }

    public void setFavPayment(String favPayment) {
        this.favPayment = favPayment;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

}

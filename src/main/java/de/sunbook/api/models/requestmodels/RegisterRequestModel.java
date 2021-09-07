package de.sunbook.api.models.requestmodels;

public class RegisterRequestModel extends AuthenticationRequestModel {

    private String lastName;
    private String firstName;

    private String street;
    private String houseNum;
    private String plz;
    private String city;
    public RegisterRequestModel() {
    }
    public RegisterRequestModel(String username, String password, String lastName, String firstName, String street,
            String houseNum, String plz, String city) {
        super(username, password);
        this.lastName = lastName;
        this.firstName = firstName;
        this.street = street;
        this.houseNum = houseNum;
        this.plz = plz;
        this.city = city;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }



}

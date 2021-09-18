package de.sunbook.api.models.responsemodels;

import de.sunbook.api.models.tablemodels.UserModel;

public class BookSingleSellerResponseModel extends UserModel {
    private String userDriscription;

    public BookSingleSellerResponseModel() {

    }

    public BookSingleSellerResponseModel(Integer userId, String firstName, String lastName, String email, String street,
            String houseNum, String plz, String role, String city, String password, String favPayment, boolean isActive,
            String shopName, String userDriscription) {
        super(userId, firstName, lastName, email, street, houseNum, plz, role, city, password, favPayment, isActive,
                shopName);
        this.userDriscription = userDriscription;
    }

    public String getUserDriscription() {
        return userDriscription;
    }

    public void setUserDriscription(String userDriscription) {
        this.userDriscription = userDriscription;
    }

}

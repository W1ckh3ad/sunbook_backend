package de.sunbook.api.models.responsemodels;

import java.util.List;

import de.sunbook.api.models.tablemodels.UserModel;

/*
This class defines the response for sellers
*/
public class SellerResponseModel extends UserReponseForShopsModel {
    private List<BookResponseModel> sellers;

    public SellerResponseModel() {
    }

    public SellerResponseModel(Integer userId, String firstName, String lastName, String email, String street,
            String houseNum, String plz, String role, String city, String shopName, List<BookResponseModel> sellers) {
        super(userId, firstName, lastName, email, street, houseNum, plz, role, city, shopName);
        this.sellers = sellers;
    }

    public SellerResponseModel(UserModel model, List<BookResponseModel> sellers) {
        this(model.getUserId(), model.getFirstName(), model.getLastName(), model.getEmail(), model.getStreet(),
                model.getHouseNum(), model.getPlz(), model.getRole(), model.getCity(), model.getShopName(), sellers);
    }

    public List<BookResponseModel> getSellers() {
        return sellers;
    }

    public void setSellers(List<BookResponseModel> sellers) {
        this.sellers = sellers;
    }

}

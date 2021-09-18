package de.sunbook.api.models.responsemodels;

import java.util.List;

import de.sunbook.api.models.tablemodels.UserModel;

public class SellerSingleResponseModel extends UserReponseForShopsModel {
    private List<SellerSingleBookResponseModel> books;

    public SellerSingleResponseModel() {
    }

    public SellerSingleResponseModel(Integer userId, String firstName, String lastName, String email, String street,
            String houseNum, String plz, String role, String city, String shopName,
            List<SellerSingleBookResponseModel> books) {
        super(userId, firstName, lastName, email, street, houseNum, plz, role, city, shopName);
        this.books = books;
    }

    public SellerSingleResponseModel(UserModel model, List<SellerSingleBookResponseModel> books) {
        this(model.getUserId(), model.getFirstName(), model.getLastName(), model.getEmail(), model.getStreet(),
                model.getHouseNum(), model.getPlz(), model.getRole(), model.getCity(), model.getShopName(), books);
    }

    public List<SellerSingleBookResponseModel> getBooks() {
        return books;
    }

    public void setBooks(List<SellerSingleBookResponseModel> sellers) {
        this.books = sellers;
    }

}

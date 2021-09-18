package de.sunbook.api.models.responsemodels;

import java.util.Date;

import de.sunbook.api.models.tablemodels.BookModel;

public class SellerSingleBookResponseModel extends BookModel {
    private String userDiscription;

    public SellerSingleBookResponseModel() {

    }

    public SellerSingleBookResponseModel(Integer uid, String genre, String title, String subtitle, String author,
            String description, String picture, String isbn, String publisher, String language, Date releaseDate,
            String binding, Float price, String userDiscription) {
        super(uid, genre, title, subtitle, author, description, picture, isbn, publisher, language, releaseDate,
                binding, price);
        this.userDiscription = userDiscription;
    }

    public String getUserDiscription() {
        return userDiscription;
    }

    public void setUserDiscription(String userDriscription) {
        this.userDiscription = userDriscription;
    }

}

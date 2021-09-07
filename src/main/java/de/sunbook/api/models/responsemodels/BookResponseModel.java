package de.sunbook.api.models.responsemodels;

import java.util.Date;

import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.models.tablemodels.UserModel;

public class BookResponseModel extends BookModel {
    private UserModel owner;
    private String userDescription;

    public BookResponseModel() {
    }

    public BookResponseModel(Integer uid, String genre, String title, String subtitle, String author,
            String description, String picture, String isbn, String publisher, String language, Date releaseDate,
            String binding, Float price, UserModel owner, String userDescription) {
        super(uid, genre, title, subtitle, author, description, picture, isbn, publisher, language, releaseDate,
                binding, price);
        this.owner = owner;
        this.userDescription = userDescription;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

}

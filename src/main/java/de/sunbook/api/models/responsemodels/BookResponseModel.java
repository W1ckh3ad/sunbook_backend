package de.sunbook.api.models.responsemodels;

import java.util.Date;

import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.models.tablemodels.UserModel;

/*
This class defines the response after a book search with a list of books
*/
public class BookResponseModel extends BookModel {
    private UserModel user;
    private String userDescription;

    public BookResponseModel() {
    }

    public BookResponseModel(Integer uid, String genre, String title, String subtitle, String author,
            String description, String picture, String isbn, String publisher, String language, Date releaseDate,
            String binding, Float price, UserModel user, String userDescription) {
        super(uid, genre, title, subtitle, author, description, picture, isbn, publisher, language, releaseDate,
                binding, price);
        this.user = user;
        this.userDescription = userDescription;
    }

    public UserModel getOwner() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

}

package de.sunbook.api.models;

import java.util.Date;

public class BookResponseModel extends BookModel {
    private UserModel owner;

    public BookResponseModel(Integer uid, String genre, String title, String subtitle, String author,
            String description, String picture, String isbn, String publisher, String language, Date releaseDate,
            String binding, Float price, UserModel owner) {
        super(uid, genre, title, subtitle, author, description, picture, isbn, publisher, language, releaseDate,
                binding, price);
        this.owner = owner;
    }

    public BookResponseModel() {
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}

package de.sunbook.api.models.requestmodels;

public class AddBookToSellRequestModel {
    private int bookId;
    private String description;

    public AddBookToSellRequestModel() {
    }

    public AddBookToSellRequestModel(int bookId, String description) {
        this.bookId = bookId;
        this.description = description;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

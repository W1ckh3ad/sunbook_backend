package de.sunbook.api.models;

/*
This is the model for buying a book
*/
public class BookBuyModel {
    private int bookId;
    private int sellerId;

    public BookBuyModel() {
    }

    public BookBuyModel(int bookId, int sellerId) {
        this.bookId = bookId;
        this.sellerId = sellerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

}

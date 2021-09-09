package de.sunbook.api.models.tablemodels;

public class OrderPartModel {
    private int id;
    private int orderId;
    private int bookId;
    private int sellerId;

    public OrderPartModel() {
    }

    public OrderPartModel(int id, int orderId, int bookId, int sellerId) {
        this.id = id;
        this.orderId = orderId;
        this.bookId = bookId;
        this.sellerId = sellerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

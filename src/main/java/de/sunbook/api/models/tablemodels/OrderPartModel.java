package de.sunbook.api.models.tablemodels;

public class OrderPartModel {
    private int id;
    private int orderId;
    private Integer bookId;
    private Integer userId;
    private Integer extraProductId;

    public OrderPartModel() {
    }

    public OrderPartModel(int id, int orderId, Integer bookId, Integer userId, Integer extraProductId) {
        this.id = id;
        this.orderId = orderId;
        this.bookId = bookId;
        this.userId = userId;
        this.extraProductId = extraProductId;
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExtraProductId() {
        return extraProductId;
    }

    public void setExtraProductId(Integer extraProductId) {
        this.extraProductId = extraProductId;
    }

}

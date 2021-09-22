package de.sunbook.api.models.tablemodels;

import java.util.Date;

/*
This is the model of the orders
*/
public class OrderModel {
    private int id;
    private int userId;
    private float value;
    private String paymentMethod;
    private Date createdAt;

    public OrderModel() {
    }

    public OrderModel(int id, int userId, float value, String paymentMethod, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.value = value;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}

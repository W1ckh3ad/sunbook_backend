package de.sunbook.api.models.tablemodels;

import java.util.Date;

public class ExtraProduct {

    private String productName;
    private String productCode;
    private Integer creator;
    private Integer receiver;
    private Date createdAt;
    private Float price;
    private String productDescription;

    public ExtraProduct() {
    }

    public ExtraProduct(String name, String code, Integer creator, Date createdAt, Integer receiver, Float price,
            String description) {
        this.productName = name;
        this.productCode = code;
        this.creator = creator;
        this.createdAt = createdAt;
        this.receiver = receiver;
        this.price = price;
        this.productDescription = description;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public String getCode() {
        return productCode;
    }

    public void setCode(String code) {
        this.productCode = code;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return productDescription;
    }

    public void setDescription(String description) {
        this.productDescription = description;
    }

}

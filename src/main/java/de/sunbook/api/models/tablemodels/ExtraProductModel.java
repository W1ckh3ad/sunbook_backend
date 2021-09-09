package de.sunbook.api.models.tablemodels;

import java.util.Date;

public class ExtraProductModel {

    private int id;
    private String productName;
    private String productCode;
    private int creator;
    private Date createdAt;
    private Integer receiver;
    private Float price;
    private Integer usedIn;
    private Date usedAt;
    private String productDescription;

    public ExtraProductModel() {
    }

    public ExtraProductModel(int id, String productName, String productCode, int creator, Date createdAt,
            Integer receiver, Float price, Integer usedIn, Date usedAt, String productDescription) {
        this.id = id;
        this.productName = productName;
        this.productCode = productCode;
        this.creator = creator;
        this.createdAt = createdAt;
        this.receiver = receiver;
        this.price = price;
        this.usedIn = usedIn;
        this.usedAt = usedAt;
        this.productDescription = productDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
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

    public Integer getUsedIn() {
        return usedIn;
    }

    public void setUsedIn(Integer usedIn) {
        this.usedIn = usedIn;
    }

    public Date getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(Date usedAt) {
        this.usedAt = usedAt;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}

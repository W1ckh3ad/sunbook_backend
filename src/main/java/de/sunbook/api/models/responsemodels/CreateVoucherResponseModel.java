package de.sunbook.api.models.responsemodels;

import java.util.Date;

/*
This class defines the response for the voucher
*/
public class CreateVoucherResponseModel {
    private String code;
    private Date createdAt;
    private String productName;
    private String receiverEmail;
    private int receiverId;

    public CreateVoucherResponseModel() {

    }

    public CreateVoucherResponseModel(String code, Date createdAt, String productName, String receiverEmail,
            int receiverId) {
        this.code = code;
        this.createdAt = createdAt;
        this.productName = productName;
        this.receiverEmail = receiverEmail;
        this.receiverId = receiverId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

}

package de.sunbook.api.models.requestmodels;

/*
This class defines the request model to create a voucher
*/
public class CreateVoucherRequestModel {
    private float value;
    private Integer receiverId;
    private String receiverEmail;
    private String description;

    public CreateVoucherRequestModel() {
    }

    public CreateVoucherRequestModel(float value, Integer receiverId, String receiverEmail, String description) {
        this.value = value;
        this.receiverId = receiverId;
        this.receiverEmail = receiverEmail;
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

package de.sunbook.api.models.requestmodels;

import java.util.List;

import de.sunbook.api.models.BookBuyModel;

/*
This class defines the request model for an order
*/
public class OrderRequestModel {
    private List<BookBuyModel> books;
    private List<CreateVoucherRequestModel> vouchers;
    private List<String> voucherCodes;
    private String paymentMethod;

    public OrderRequestModel() {
    }

    public OrderRequestModel(List<BookBuyModel> books, List<CreateVoucherRequestModel> vouchers,
            List<String> voucherCodes, String paymentMethod) {
        this.books = books;
        this.vouchers = vouchers;
        this.voucherCodes = voucherCodes;
        this.paymentMethod = paymentMethod;
    }

    public List<BookBuyModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookBuyModel> books) {
        this.books = books;
    }

    public List<CreateVoucherRequestModel> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<CreateVoucherRequestModel> vouchers) {
        this.vouchers = vouchers;
    }

    public List<String> getVoucherCodes() {
        return voucherCodes;
    }

    public void setVoucherCodes(List<String> voucherCodes) {
        this.voucherCodes = voucherCodes;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}

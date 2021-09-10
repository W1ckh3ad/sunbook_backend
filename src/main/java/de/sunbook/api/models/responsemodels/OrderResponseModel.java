package de.sunbook.api.models.responsemodels;

import java.util.List;

import de.sunbook.api.models.tablemodels.ExtraProductModel;
import de.sunbook.api.models.tablemodels.OrderModel;

public class OrderResponseModel extends OrderModel {
    private List<BookResponseModel> books;
    private List<ExtraProductModel> codes;
    List<ExtraProductModel> usedCodes;

    public OrderResponseModel() {

    }

    public OrderResponseModel(int id, int userId, float value, String paymentMethod, List<BookResponseModel> books,
            List<ExtraProductModel> codes, List<ExtraProductModel> usedCodes) {
        super(id, userId, value, paymentMethod);
        this.books = books;
        this.codes = codes;
        this.usedCodes = usedCodes;
    }

    public List<BookResponseModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponseModel> books) {
        this.books = books;
    }

    public List<ExtraProductModel> getCodes() {
        return codes;
    }

    public void setCodes(List<ExtraProductModel> codes) {
        this.codes = codes;
    }

    public List<ExtraProductModel> getUsedCodes() {
        return usedCodes;
    }

    public void setUsedCodes(List<ExtraProductModel> usedCodes) {
        this.usedCodes = usedCodes;
    }

}

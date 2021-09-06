package de.sunbook.api.models;

public class BookQueryModel {
    private String genre;
    private String binding;
    private Float maxPrice;
    private Float minPrice;

    public BookQueryModel() {
    }

    public BookQueryModel(String genre, String binding, Float maxPrice, Float minPrice) {
        this.genre = genre;
        this.binding = binding;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }
}

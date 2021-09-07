package de.sunbook.api.models.tablemodels;

import java.util.Date;

public class BookModel {
    private Integer uid;
    private String genre;
    private String title;
    private String subtitle;
    private String author;
    private String description;
    private String picture;
    private String isbn;
    private String publisher;
    private String language;
    private Date releaseDate;
    private String binding;
    private Float price;

    public BookModel() {
    }

    public BookModel(Integer uid, String genre, String title, String subtitle, String author, String description,
            String picture, String isbn, String publisher, String language, Date releaseDate, String binding,
            Float price) {
        this.uid = uid;
        this.genre = genre;
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.description = description;
        this.picture = picture;
        this.isbn = isbn;
        this.publisher = publisher;
        this.language = language;
        this.releaseDate = releaseDate;
        this.binding = binding;
        this.price = price;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

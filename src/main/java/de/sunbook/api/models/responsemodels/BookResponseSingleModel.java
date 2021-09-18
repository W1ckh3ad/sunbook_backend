package de.sunbook.api.models.responsemodels;

import java.util.Date;
import java.util.List;

import de.sunbook.api.models.tablemodels.BookModel;

public class BookResponseSingleModel extends BookModel {
    private List<UserBookModelForBookResponseSingleModel> sellers;

    public BookResponseSingleModel() {
    }

    public BookResponseSingleModel(Integer uid, String genre, String title, String subtitle, String author,
            String description, String picture, String isbn, String publisher, String language, Date releaseDate,
            String binding, Float price, List<UserBookModelForBookResponseSingleModel> sellers) {
        super(uid, genre, title, subtitle, author, description, picture, isbn, publisher, language, releaseDate,
                binding, price);
        this.sellers = sellers;
    }

    public BookResponseSingleModel(BookModel model, List<UserBookModelForBookResponseSingleModel> sellers) {
        this(model.getUid(), model.getGenre(), model.getTitle(), model.getSubtitle(), model.getAuthor(),
                model.getDescription(), model.getPicture(), model.getIsbn(), model.getPublisher(), model.getLanguage(),
                model.getReleaseDate(), model.getBinding(), model.getPrice(), sellers);
    }

    public List<UserBookModelForBookResponseSingleModel> getSellers() {
        return sellers;
    }

    public void setSellers(List<UserBookModelForBookResponseSingleModel> sellers) {
        this.sellers = sellers;
    }

}

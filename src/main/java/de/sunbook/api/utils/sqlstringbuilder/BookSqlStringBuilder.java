package de.sunbook.api.utils.sqlstringbuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class BookSqlStringBuilder extends SqlStringBuilder {
    public BookSqlStringBuilder(String table) {
        super(table, "uid");
    }

    public String insert(BookModel model) {

        return insertHelper(getMap(model));
    }

    public String update(BookModel model) {
        return updateHelper(getMap(model), String.valueOf(model.getUid()));
    }

    private Map<String, String> getMap(BookModel model) {
        Map<String, String> map = new HashMap<String, String>();
        String author = model.getAuthor();
        String description = model.getDescription();
        String genre = model.getGenre();
        String isbn = model.getIsbn();
        String language = model.getLanguage();
        String picture = model.getPicture();
        String publisher = model.getPublisher();
        Date releaseDate = model.getReleaseDate();
        String subtitle = model.getSubtitle();
        String title = model.getTitle();
        Float price = model.getPrice();

        if (author != null) {
            map.put("author", stringValue(author));
        }
        if (description != null) {
            map.put("description", stringValue(description));
        }
        if (genre != null) {
            map.put("genre", stringValue(genre));
        }
        if (isbn != null) {
            map.put("isbn", stringValue(isbn));
        }
        if (language != null) {
            map.put("language", stringValue(language));
        }
        if (picture != null) {
            map.put("picture", stringValue(picture));
        }
        if (publisher != null) {
            map.put("publisher", stringValue(publisher));
        }
        if (releaseDate != null) {
            map.put("releaseDate", DateToString(releaseDate));
        }
        if (subtitle != null) {
            map.put("subtitle", stringValue(subtitle));
        }
        if (title != null) {
            map.put("title", stringValue(title));
        }
        if (price != null) {
            map.put("price", price.toString());
        }
        if (map.size() == 0) {
            throw new Error("Invalid Request Body");
        }
        return map;

    }

    public String select(BookQueryModel model) {
        return select() + getBookQueryModelWhereClause(model);
    }

    public String selectIsbn(String isbn) {
        return select() + WHERE + wrap("isbn") + EQUALS + stringValue(isbn);
    }
}

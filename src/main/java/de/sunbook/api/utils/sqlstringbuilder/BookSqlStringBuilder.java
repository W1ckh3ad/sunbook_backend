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
        Map<String, String> map = new HashMap<String, String>();
        return updateHelper(map, String.valueOf(model.getUid()));
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
            map.put("author", author);
        }
        if (description != null) {
            map.put("description", description);
        }
        if (genre != null) {
            map.put("genre", genre);
        }
        if (isbn != null) {
            map.put("isbn", isbn);
        }
        if (language != null) {
            map.put("language", language);
        }
        if (picture != null) {
            map.put("picture", picture);
        }
        if (publisher != null) {
            map.put("publisher", publisher);
        }
        if (releaseDate != null) {
            map.put("releaseDate", DateToString(releaseDate));
        }
        if (subtitle != null) {
            map.put("subtitle", subtitle);
        }
        if (title != null) {
            map.put("title", title);
        }
        if (price != null) {
            map.put("price", price.toString());
        }

        return map;

    }

    public String select(BookQueryModel model) {
        return select() + getBookQueryModelWhereClause(model);
    }
}

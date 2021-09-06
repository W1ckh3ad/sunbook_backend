package de.sunbook.api.utils.sqlstringbuilder;

import de.sunbook.api.models.BookModel;
import de.sunbook.api.models.BookQueryModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

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

    public String select(BookQueryModel model) {
        String genre = model.getGenre();
        String binding = model.getBinding();
        Float maxPrice = model.getMaxPrice();
        Float minPrice = model.getMinPrice();
        String sql = "";
        // add the statement for optional query for Price
        if (((Float) maxPrice != null) && ((Float) minPrice != null)) {
            sql += " where price between " + minPrice + " and " + maxPrice; // between min and max
        } else {
            if ((Float) minPrice != null) {
                Float a = minPrice;
                sql += " where price >= " + a.toString();
            }
            if ((Float) maxPrice != null) {
                Float b = maxPrice;
                sql += " where price <= " + b.toString();
            }
        }
        // ergänzt die Query um die weiteren optionalen Parameter
        if (((Float) maxPrice == null) && ((Float) minPrice == null)) {
            if (binding != null) {
                sql += " where binding = '" + binding + "'";
            }
            if (genre != null && binding != null) {
                sql += " and genre = '" + genre + "'";
            } else if (genre != null && binding == null) {
                sql += " where genre = '" + genre + "'";
            } else {
                // wenn indexpage && bindung = null, oder wenn genre == null && binding != null
                // dann Query nicht weiter ergänzen
            }
        } else {
            if (binding != null) {
                sql += " and binding = '" + binding + "'";
            }
            if (genre != null) {
                sql += " and genre = '" + genre + "'";
            }
        }
        // List<BookModel> listOfBooks = BookService.searchBook(sql);
        // return listOfBooks;
        return sql;
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
            map.put("releaseDate", releaseDate.toString());
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
}

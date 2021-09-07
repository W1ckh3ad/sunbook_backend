package de.sunbook.api.utils.sqlstringbuilder.abstracts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.sunbook.api.models.requestmodels.BookQueryModel;

public class SqlStringBuilder {
    protected String table;
    protected String keyColumn;
    protected final String SELECT = " SELECT ";
    protected final String FROM = " FROM ";
    protected final String INSERT = " INSERT INTO ";
    protected final String DELETE = " DELETE ";
    protected final String UPDATE = " UPDATE ";
    protected final String SET = " SET ";
    protected final String WHERE = " WHERE ";
    protected final String EVERYTHING = " * ";
    protected final String EQUALS = " = ";
    protected final String OPEN = " ( ";
    protected final String CLOSE = " ) ";

    public SqlStringBuilder(String table, String keyColumn) {
        this.table = wrap(table);
        this.keyColumn = keyColumn;
    }

    protected String wrap(String s) {
        return " `" + s + "` ";
    }

    protected String wrap(String s, String table) {
        return " " + table + ".`" + s + "` ";
    }

    protected List<String> wrap(List<String> columns) {
        List<String> returnList = new ArrayList<String>();
        for (String column : columns) {
            returnList.add(wrap(column));
        }
        return returnList;
    }

    protected String stringValue(String s) {
        return " '" + s + "' ";
    }

    protected List<String> stringValue(List<String> columns) {
        List<String> returnList = new ArrayList<String>();
        for (String column : columns) {
            returnList.add(stringValue(column));
        }
        return returnList;
    }

    protected String setStringBuilder(Map<String, String> map) {
        List<String> sets = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sets.add(wrap(entry.getKey()) + EQUALS + stringValue(entry.getValue()));
        }
        return String.join(", ", sets);
    }

    protected String insertHelper(Map<String, String> map) {
        List<String> cols = new ArrayList<String>(map.keySet());
        List<String> values = new ArrayList<String>(map.values());
        return INSERT + table + OPEN + String.join(", ", wrap(cols)) + CLOSE + "VALUES " + OPEN
                + String.join(", ", stringValue(values)) + CLOSE;
    }

    protected String updateHelper(Map<String, String> map, String uid) {
        return UPDATE + table + String.join(", ", setStringBuilder(map)) + WHERE + keyColumn + EQUALS
                + stringValue(uid);
    }

    public String select() {
        return SELECT + EVERYTHING + FROM + table;
    }

    public String select(String value) {
        return select() + WHERE + wrap(keyColumn) + EQUALS + stringValue(value);
    }

    public String delete(String value) {
        return DELETE + table + WHERE + wrap(keyColumn) + EQUALS + stringValue(value);
    }

    protected String DateTimeToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        var dateString = dateFormat.format(date);
        return " STR_TO_DATE('" + dateString + "', '%Y-%m-%d %H:%M:%s')";
    }

    protected String DateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        var dateString = dateFormat.format(date);
        return " STR_TO_DATE('" + dateString + "', '%Y-%m-%d')";
    }

    protected String getBookQueryModelWhereClause(BookQueryModel model) {
        String genre = model.getGenre();
        String binding = model.getBinding();
        Float maxPrice = model.getMaxPrice();
        Float minPrice = model.getMinPrice();
        var parts = new ArrayList<String>();
        if (maxPrice != null && minPrice != null) {
            parts.add(" price between " + minPrice + " and " + maxPrice + " ");
        } else {
            if (minPrice != null) {
                parts.add(" price >= " + minPrice + " ");
            }
            if (maxPrice != null) {
                parts.add(" price >= " + maxPrice + " ");
            }
        }
        if (binding != null) {
            parts.add(" binding = " + stringValue(binding) + " ");
        }
        if (genre != null) {
            parts.add(" genre = " + stringValue(genre) + " ");
        }
        return parts.size() > 0 ? WHERE + String.join(" AND ", parts) : "";
    }
}

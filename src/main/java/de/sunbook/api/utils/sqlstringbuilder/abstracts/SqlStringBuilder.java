package de.sunbook.api.utils.sqlstringbuilder.abstracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return INSERT + table + OPEN + String.join(", ", wrap(cols)) + CLOSE + "VALUES " + OPEN + stringValue(values)
                + CLOSE;
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
}

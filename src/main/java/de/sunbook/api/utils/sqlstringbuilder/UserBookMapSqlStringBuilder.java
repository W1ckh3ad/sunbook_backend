package de.sunbook.api.utils.sqlstringbuilder;

import java.util.HashMap;
import java.util.Map;

import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.tablemodels.UserBookMapModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

/*
This is the String Builder for the Query to the User Book Map Database
*/
public class UserBookMapSqlStringBuilder extends SqlStringBuilder {
    private final String baseSql = "SELECT * FROM UserMapBook LEFT JOIN BOOK ON BOOK.uid = UserMapBook.uid LEFT JOIN USERS ON USERS.userID = UserMapBook.userID";

    public UserBookMapSqlStringBuilder() {
        super("UserMapBook", "keyColumn");
    }

    public String select(BookQueryModel model) {
        return baseSql + getBookQueryModelWhereClause(model);
    }

    public String selectWithUsername(String username) {
        return baseSql + WHERE + wrap("email", "USERS") + EQUALS + stringValue(username);
    }

    public String selectWithUserId(int id) {
        return baseSql + WHERE + wrap("userId", "USERS") + EQUALS + stringValue(id);
    }

    public String selectWithUserIdAndBookId(int id, int bookId) {
        return baseSql + WHERE + wrap("userId", "USERS") + EQUALS + stringValue(id) + AND + wrap("uid", "BOOK") + EQUALS
                + stringValue(bookId);
    }

    public String selectWithBook(int id) {
        return baseSql + WHERE + wrap("uid", "BOOK") + EQUALS + stringValue(id);
    }

    public String select(int bookId, int userId) {
        return select() + getWhereString(bookId, userId);
    }

    public String delete(int bookId, int userId) {
        return DELETE + table + getWhereString(bookId, userId);
    }

    public String insert(UserBookMapModel model) {
        return insertHelper(getMap(model));
    }

    public String update(UserBookMapModel model) {
        return UPDATE + table + SET + wrap("userDescription") + EQUALS + stringValue(model.getUserDescription())
                + getWhereString(model.getUid(), model.getUserId());
    }

    private Map<String, String> getMap(UserBookMapModel model) {
        Map<String, String> map = new HashMap<String, String>();
        String userDescription = model.getUserDescription();
        int uid = model.getUid();
        int userId = model.getUserId();

        map.put("userId", stringValue(userId));

        if (userDescription != null) {
            map.put("userDescription", stringValue(userDescription));
        }
        map.put("uid", stringValue(uid));

        return map;
    }

    private String getWhereString(int bookId, int userId) {
        return WHERE + wrap("uid") + EQUALS + stringValue(bookId) + AND + wrap("userId") + EQUALS + stringValue(userId);
    }
}

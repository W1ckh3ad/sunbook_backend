package de.sunbook.api.utils.sqlstringbuilder;

import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class UserBookMapSqlStringBuilder extends SqlStringBuilder {
    private final String baseSql = "SELECT * FROM UserMapBook LEFT JOIN BOOK ON BOOK.uid = UserMapBook.uid LEFT JOIN USERS ON USERS.userID = UserMapBook.userID";

    public UserBookMapSqlStringBuilder() {
        super("table", "keyColumn");
    }

    public String select(BookQueryModel model) {
        return baseSql + getBookQueryModelWhereClause(model);
    }

    public String selectWithUsername(String username) {
        return baseSql + WHERE + wrap("email", "USERS") + EQUALS + stringValue(username);
    }

    public String selectWithUserId(int id) {
        return baseSql + WHERE + wrap("userId", "USERS") + EQUALS + stringValue(String.valueOf(id));
    }

    public String selectWithBook(int id) {
        return baseSql + WHERE + wrap("uid", "BOOK") + EQUALS + stringValue(String.valueOf(id));
    }
}

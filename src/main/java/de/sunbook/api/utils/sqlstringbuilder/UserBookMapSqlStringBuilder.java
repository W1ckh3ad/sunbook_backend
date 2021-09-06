package de.sunbook.api.utils.sqlstringbuilder;

import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class UserBookMapSqlStringBuilder extends SqlStringBuilder {
    public UserBookMapSqlStringBuilder(String table) {
        super(table, "uid");
    }
}

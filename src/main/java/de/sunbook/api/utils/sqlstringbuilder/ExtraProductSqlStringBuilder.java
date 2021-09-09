package de.sunbook.api.utils.sqlstringbuilder;

import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class ExtraProductSqlStringBuilder extends SqlStringBuilder {
    public ExtraProductSqlStringBuilder(String table) {
        super(table, "id");
    }

    
}

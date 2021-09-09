package de.sunbook.api.processors;

import java.sql.SQLException;

import de.sunbook.api.models.tablemodels.OrderPartModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.utils.sqlstringbuilder.OrderPartSqlStringBuilder;

public class OrderPartProcessor extends Processor {

    OrderPartSqlStringBuilder sqlStringBuilder;

    public OrderPartProcessor() {
        super("ORDERS_PARTS");
        sqlStringBuilder = new OrderPartSqlStringBuilder(table);
    }

    public int insert(OrderPartModel model) throws SQLException {
        connection.execute(sqlStringBuilder.insert(model));
return 0;
    }

}

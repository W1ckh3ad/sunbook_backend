package de.sunbook.api.utils.sqlstringbuilder;

import java.util.HashMap;
import java.util.Map;

import de.sunbook.api.models.tablemodels.OrderPartModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class OrderPartSqlStringBuilder extends SqlStringBuilder {

    public OrderPartSqlStringBuilder(String table) {
        super(table, "id");
    }

    public String insert(OrderPartModel model) {
        return insertHelper(getMap(model));
    }

    private Map<String, String> getMap(OrderPartModel model) {
        Map<String, String> map = new HashMap<String, String>();
        int orderId = model.getOrderId();
        int bookId = model.getBookId();
        int sellerId = model.getUserId();

        map.put("orderId", stringValue(orderId));
        map.put("bookId", stringValue(bookId));
        map.put("userId", stringValue(sellerId));
        return map;
    }
}

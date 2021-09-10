package de.sunbook.api.utils.sqlstringbuilder;

import java.util.HashMap;
import java.util.Map;

import de.sunbook.api.models.tablemodels.OrderPartModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class OrderPartSqlStringBuilder extends SqlStringBuilder {

    public OrderPartSqlStringBuilder(String table) {
        super(table, "id");
    }

    public String selectOrderId(int orderId) {
        return select() + WHERE + wrap("orderId") + EQUALS + stringValue(orderId);
    }

    public String insert(OrderPartModel model) {
        return insertHelper(getMap(model));
    }

    private Map<String, String> getMap(OrderPartModel model) {
        Map<String, String> map = new HashMap<String, String>();
        int orderId = model.getOrderId();
        Integer bookId = model.getBookId();
        Integer sellerId = model.getUserId();
        Integer extraProductId = model.getExtraProductId();
        map.put("orderId", stringValue(orderId));
        if (bookId != null) {
            map.put("bookId", stringValue(bookId));
        }
        if (sellerId != null) {
            map.put("userId", stringValue(sellerId));
        }
        if (extraProductId != null) {
            map.put("extraProductId", stringValue(extraProductId));
        }

        return map;
    }
}

package de.sunbook.api.utils.sqlstringbuilder;

import java.util.HashMap;
import java.util.Map;

import de.sunbook.api.models.tablemodels.OrderModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class OrderSqlStringBuilder extends SqlStringBuilder {

    public OrderSqlStringBuilder(String table) {
        super(table, "id");
    }

    public String insert(OrderModel model) {
        return insertHelper(getMap(model));
    }

    public String selectUserId(int userId) {
        return select() + WHERE + wrap("userId") + EQUALS + stringValue(userId);
    }

    private Map<String, String> getMap(OrderModel model) {
        var map = new HashMap<String, String>();
        var paymentMethod = model.getPaymentMethod();
        var userId = model.getUserId();
        var value = model.getValue();
        var createdAt = model.getCreatedAt();

        map.put("paymentMethod", stringValue(paymentMethod));
        map.put("userId", stringValue(userId));
        map.put("value", stringValue(value));
        map.put("createdAt", dateTimeToString(createdAt));

        return map;
    }
}

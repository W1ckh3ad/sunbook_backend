package de.sunbook.api.utils.sqlstringbuilder;

import java.util.HashMap;
import java.util.Map;

import de.sunbook.api.models.tablemodels.OrderModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class OrderSqlStringBuilder extends SqlStringBuilder {

    public OrderSqlStringBuilder(String table) {
        super(table, "id");
    }

    private final String baseSql = "";

    public String insert(OrderModel model) {
        return insertHelper(getMap(model));
    }

    @Override
    public String select() {
        return baseSql;
    }

    @Override
    public String select(int id) {
        return baseSql + WHERE + wrap(keyColumn) + EQUALS + stringValue(id);
    }

    private Map<String, String> getMap(OrderModel model) {
        var map = new HashMap<String, String>();
        var paymentMethod = model.getPaymentMethod();
        var userId = model.getUserId();
        var value = model.getValue();

        map.put("paymentMethod", stringValue(paymentMethod));
        map.put("userId", stringValue(userId));
        map.put("value", stringValue(value));

        return map;
    }
}

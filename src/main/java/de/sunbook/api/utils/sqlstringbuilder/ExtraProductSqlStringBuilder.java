package de.sunbook.api.utils.sqlstringbuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.sunbook.api.models.tablemodels.ExtraProductModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class ExtraProductSqlStringBuilder extends SqlStringBuilder {
    public ExtraProductSqlStringBuilder(String table) {
        super(table, "id");
    }

    public String insert(ExtraProductModel model) {
        return insertHelper(getMap(model));
    }

    public String update(ExtraProductModel model) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("usedIn", stringValue(model.getUsedIn()));
        map.put("usedAt", dateTimeToString(model.getUsedAt()));
        return updateHelper(map, String.valueOf(model.getId()));
    }

    public String selectUsedForOrderId(int id){
        return select() + WHERE + wrap("usedIn") + EQUALS + stringValue(id);
    }

    private Map<String, String> getMap(ExtraProductModel model) {
        Map<String, String> map = new HashMap<String, String>();

        String productCode = model.getProductCode();
        String productDescription = model.getProductDescription();
        String productName = model.getProductName();
        Date createdAt = model.getCreatedAt();
        int creator = model.getCreator();
        Integer receiver = model.getReceiver();
        float price = model.getPrice();

        map.put("creator", stringValue(creator));
        map.put("price", stringValue(price));

        if (productCode != null) {
            map.put("productCode", stringValue(productCode));
        }
        if (productDescription != null) {
            map.put("productDescription", stringValue(productDescription));
        }
        if (productName != null) {
            map.put("productName", stringValue(productName));
        }
        if (receiver != null) {
            map.put("receiver", stringValue(receiver));
        }
        if (createdAt != null) {
            map.put("createdAt", dateTimeToString(createdAt));
        }

        return map;
    }

    public String select(String code) {
        return select() + WHERE + wrap("productCode") + EQUALS + stringValue(code);
    }

}

package de.sunbook.api.utils.sqlstringbuilder;

import java.util.HashMap;
import java.util.Map;

import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

public class UserSqlStringBuilder extends SqlStringBuilder {

    public UserSqlStringBuilder(String table, String keyColumn) {
        super(table, keyColumn);
    }

    public String insert(UserModel model) {
        var map = new HashMap<String, String>();
        map.put("email", stringValue(model.getEmail()));
        map.put("password", stringValue(model.getPassword()));
        map.put("city", stringValue(model.getCity()));
        map.put("plz", stringValue(model.getPlz()));
        map.put("houseNum", stringValue(model.getHouseNum()));
        map.put("street", stringValue(model.getStreet()));
        map.put("firstName", stringValue(model.getFirstName()));
        map.put("lastName", stringValue(model.getLastName()));
        map.put("isActive", "1");
        return insertHelper(map);
    }

    public String update(UserModel model) {

        Map<String, String> map = getMap(model);
        return updateHelper(map, String.valueOf(model.getUserId()));
    }

    public String getUsername(String email) {
        return select() + WHERE + "email" + EQUALS + stringValue(email);
    }

    public String insertAll(UserModel model) {
        return insertHelper(getMap(model));
    }

    private Map<String, String> getMap(UserModel model) {
        Map<String, String> map = new HashMap<String, String>();
        String email = model.getEmail();
        String favPayment = model.getFavPayment();
        String firstName = model.getFirstName();
        String houseNum = model.getHouseNum();
        String lastName = model.getLastName();
        String password = model.getPassword();
        String plz = model.getPlz();
        String role = model.getRole();
        String street = model.getStreet();
        String city = model.getCity();
        Boolean isActive = model.isActive();
        String shopName = model.getShopName();

        if (firstName != null) {
            map.put("firstName", stringValue(firstName));
        }
        if (lastName != null) {
            map.put("lastName", stringValue(lastName));
        }
        if (email != null) {
            map.put("email", stringValue(email));
        }
        if (street != null) {
            map.put("street", stringValue(street));
        }
        if (password != null) {
            map.put("password", stringValue(password));
        }
        if (favPayment != null) {
            map.put("favPayment", stringValue(favPayment));
        }
        if (houseNum != null) {
            map.put("houseNum", stringValue(houseNum));
        }
        if (plz != null) {
            map.put("plz", stringValue(plz));
        }
        if (city != null) {
            map.put("city", stringValue(city));
        }
        if (role != null) {
            map.put("role", stringValue(role));
        }
        if (shopName != null) {
            map.put("shopName", stringValue(shopName));
        }
        if (isActive != null) {
            map.put("isActive", isActive ? stringValue("1") : stringValue("0"));
        } else {
            map.put("isActive", stringValue("0"));
        }
        if (map.size() == 1) {
            throw new Error("Invalid Request Body");
        }
        return map;
    }
}

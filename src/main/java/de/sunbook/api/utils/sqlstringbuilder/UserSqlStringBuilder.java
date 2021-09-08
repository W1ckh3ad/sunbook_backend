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
        map.put("email", model.getEmail());
        map.put("password", model.getPassword());
        map.put("city", model.getCity());
        map.put("plz", model.getPlz());
        map.put("houseNum", model.getHouseNum());
        map.put("street", model.getStreet());
        map.put("firstName", model.getFirstName());
        map.put("lastName", model.getLastName());
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

        if (firstName != null) {
            map.put("firstName", firstName);
        }
        if (lastName != null) {
            map.put("lastName", lastName);
        }
        if (email != null) {
            map.put("email", email);
        }
        if (street != null) {
            map.put("street", street);
        }
        if (password != null) {
            map.put("password", password);
        }
        if (favPayment != null) {
            map.put("favPayment", favPayment);
        }
        if (houseNum != null) {
            map.put("houseNum", houseNum);
        }
        if (plz != null) {
            map.put("plz", plz);
        }
        if (city != null) {
            map.put("city", city);
        }
        if (role != null) {
            map.put("role", role);
        }
        if (isActive != null) {
            map.put("isActive", isActive ? "1" : "0");
        } else {
            map.put("isActive", "0");
        }
        return map;
    }
}

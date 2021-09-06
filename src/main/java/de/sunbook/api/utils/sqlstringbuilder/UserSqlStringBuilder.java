package de.sunbook.api.utils.sqlstringbuilder;

import de.sunbook.api.models.UserModel;
import de.sunbook.api.utils.sqlstringbuilder.abstracts.SqlStringBuilder;

import java.util.Map;
import java.util.HashMap;

public class UserSqlStringBuilder extends SqlStringBuilder {

    public UserSqlStringBuilder(String table, String keyColumn) {
        super(table, keyColumn);
    }

    public String insert(UserModel model) {
        return insertHelper(getMap(model));
    }

    public String update(UserModel model) {

        Map<String, String> map = new HashMap<String, String>();
        return updateHelper(map, String.valueOf(model.getUserId()));
    }

    public String checkLogin() {
        return "";
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
        if (role != null) {
            map.put("role", role);
        }

        return map;
    }
}

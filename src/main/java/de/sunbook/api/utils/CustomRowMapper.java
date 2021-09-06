package de.sunbook.api.utils;

import de.sunbook.api.models.UserModel;
import de.sunbook.api.models.BookModel;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CustomRowMapper {
    public static RowMapper<UserModel> GetUserMapper() {
        return new RowMapper<UserModel>() {
            @Override
            public UserModel mapRow(ResultSet result, int rowNum) throws SQLException {
                UserModel user = new UserModel();
                user.setUserId(result.getInt("userId"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));
                user.setStreet(result.getString("street"));
                user.setHouseNum(result.getString("houseNum"));
                user.setPlz(result.getString("plz"));
                user.setRole(result.getString("role"));
                user.setPassword(result.getString("password"));
                user.setFavPayment(result.getString("favPayment"));
                user.setActive(result.getBoolean("isActive"));
                return user;
            }
        };
    }

    public static RowMapper<BookModel> GetBookMapper() {
        return new RowMapper<BookModel>() {
            @Override
            public BookModel mapRow(ResultSet result, int rowNum) throws SQLException {
                BookModel book = new BookModel();
                book.setUid(result.getInt("uid"));
                book.setGenre(result.getString("genre"));
                book.setTitle(result.getString("title"));
                book.setSubtitle(result.getString("subtitle"));
                book.setAuthor(result.getString("author"));
                book.setDescription(result.getString("description"));
                book.setPicture(result.getString("picture"));
                book.setIsbn(result.getString("isbn"));
                book.setPublisher(result.getString("publisher"));
                book.setLanguage(result.getString("language"));
                book.setReleaseDate(result.getDate("releasedate"));
                book.setBinding(result.getString("binding"));
                book.setPrice(result.getFloat("price"));
                return book;
            }
        };
    }
}

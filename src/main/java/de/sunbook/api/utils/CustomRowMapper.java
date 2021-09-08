package de.sunbook.api.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.sunbook.api.models.responsemodels.BookResponseModel;
import de.sunbook.api.models.responsemodels.UserBookModelForBookResponseSingleModel;
import de.sunbook.api.models.responsemodels.UserReponseForShopsModel;
import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.models.tablemodels.UserBookMapModel;
import de.sunbook.api.models.tablemodels.UserModel;

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

    public static RowMapper<BookResponseModel> GetBookResponseModelRowMapper() {
        return new RowMapper<BookResponseModel>() {
            @Override
            public BookResponseModel mapRow(ResultSet result, int rowNum) throws SQLException {
                BookResponseModel book = new BookResponseModel();
                var user = new UserModel();
                book.setUserDescription(result.getString("userDescription"));
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

                user.setUserId(result.getInt("userId"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));
                user.setStreet(result.getString("street"));
                user.setHouseNum(result.getString("houseNum"));
                user.setPlz(result.getString("plz"));
                user.setRole(result.getString("role"));
                user.setShopName(result.getString("shopName"));
                book.setOwner(user);
                return book;
            }
        };
    }

    public static RowMapper<BookResponseModel> GetUserBookMapRowMapperWithoutUser() {
        return new RowMapper<BookResponseModel>() {
            @Override
            public BookResponseModel mapRow(ResultSet result, int rowNum) throws SQLException {
                BookResponseModel book = new BookResponseModel();
                book.setUserDescription(result.getString("userDescription"));
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

    public static RowMapper<UserBookModelForBookResponseSingleModel> GetUserBookModelForBookResponseSingleModelRowMapp() {
        return new RowMapper<UserBookModelForBookResponseSingleModel>() {
            @Override
            public UserBookModelForBookResponseSingleModel mapRow(ResultSet result, int rowNum) throws SQLException {
                UserBookModelForBookResponseSingleModel book = new UserBookModelForBookResponseSingleModel();

                book.setUserDescription(result.getString("userDescription"));
                var user = new UserReponseForShopsModel();

                user.setUserId(result.getInt("userId"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));
                user.setStreet(result.getString("street"));
                user.setHouseNum(result.getString("houseNum"));
                user.setPlz(result.getString("plz"));
                user.setRole(result.getString("role"));
                user.setShopName(result.getString("shopName"));
                book.setUser(user);
                return book;
            }
        };
    }

    public static RowMapper<UserBookMapModel> GetUserBookMapModelRowMapper() {
        return new RowMapper<UserBookMapModel>() {
            @Override
            public UserBookMapModel mapRow(ResultSet result, int rowNum) throws SQLException {
                UserBookMapModel book = new UserBookMapModel();

                book.setUserDescription(result.getString("userDescription"));
                book.setUid(result.getInt("uid"));
                book.setUserId(result.getInt("userId"));
                return book;
            }
        };
    }
}

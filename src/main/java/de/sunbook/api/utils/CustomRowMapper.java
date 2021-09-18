package de.sunbook.api.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import de.sunbook.api.models.responsemodels.BookResponseModel;
import de.sunbook.api.models.responsemodels.BookSingleSellerResponseModel;
import de.sunbook.api.models.responsemodels.SellerSingleBookResponseModel;
import de.sunbook.api.models.responsemodels.UserBookModelForBookResponseSingleModel;
import de.sunbook.api.models.responsemodels.UserReponseForShopsModel;
import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.models.tablemodels.ExtraProductModel;
import de.sunbook.api.models.tablemodels.OrderModel;
import de.sunbook.api.models.tablemodels.OrderPartModel;
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
                user.setCity(result.getString("city"));
                user.setShopName(result.getString("shopName"));
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
                user.setCity(result.getString("city"));
                book.setUser(user);
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

                var user = new UserModel();

                user.setUserId(result.getInt("userId"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));
                user.setStreet(result.getString("street"));
                user.setHouseNum(result.getString("houseNum"));
                user.setPlz(result.getString("plz"));
                user.setRole(result.getString("role"));
                user.setShopName(result.getString("shopName"));
                user.setCity(result.getString("city"));
                book.setUser(user);
                return book;
            }
        };
    }

    public static RowMapper<BookSingleSellerResponseModel> getBookSingleSellerResponseModelRowMapper() {
        return new RowMapper<BookSingleSellerResponseModel>() {
            @Override
            public BookSingleSellerResponseModel mapRow(ResultSet result, int rowNum) throws SQLException {
                var user = new BookSingleSellerResponseModel();
                user.setUserDriscription(result.getString("userDescription"));
                user.setUserId(result.getInt("userId"));
                user.setFirstName(result.getString("firstName"));
                user.setLastName(result.getString("lastName"));
                user.setEmail(result.getString("email"));
                user.setStreet(result.getString("street"));
                user.setHouseNum(result.getString("houseNum"));
                user.setPlz(result.getString("plz"));
                user.setRole(result.getString("role"));
                user.setShopName(result.getString("shopName"));
                user.setCity(result.getString("city"));
                return user;
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
                user.setCity(result.getString("city"));
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

    public static RowMapper<ExtraProductModel> GetExtraProductRowMapper() {
        return new RowMapper<ExtraProductModel>() {
            @Override
            public ExtraProductModel mapRow(ResultSet result, int rowNum) throws SQLException {
                ExtraProductModel model = new ExtraProductModel();

                model.setCreatedAt(result.getDate("createdAt"));
                model.setCreator(result.getInt("creator"));
                model.setId(result.getInt("id"));
                model.setPrice(result.getFloat("price"));
                model.setProductCode(result.getString("productCode"));
                model.setProductDescription(result.getString("productDescription"));
                model.setProductName(result.getString("productName"));
                model.setReceiver(result.getInt("receiver"));
                model.setUsedAt(result.getDate("usedAt"));
                model.setUsedIn(result.getInt("usedIn"));
                return model;
            }
        };
    }

    public static RowMapper<OrderModel> GetOrderModelRowMapper() {
        return new RowMapper<OrderModel>() {
            @Override
            public OrderModel mapRow(ResultSet result, int rowNum) throws SQLException {
                OrderModel model = new OrderModel();

                model.setPaymentMethod(result.getString("paymentMethod"));
                model.setId(result.getInt("id"));
                model.setUserId(result.getInt("userId"));
                model.setValue(result.getFloat("value"));
                model.setCreatedAt(result.getDate("createdAt"));
                return model;
            }
        };
    }

    public static RowMapper<OrderPartModel> getOrderPartModelRowMapper() {
        return new RowMapper<OrderPartModel>() {
            @Override
            public OrderPartModel mapRow(ResultSet result, int rowNum) throws SQLException {
                OrderPartModel model = new OrderPartModel();

                model.setBookId(result.getInt("bookId"));
                model.setOrderId(result.getInt("orderId"));
                model.setId(result.getInt("id"));
                model.setUserId(result.getInt("userId"));
                model.setExtraProductId(result.getInt("extraProductId"));
                return model;
            }
        };
    }

    public static RowMapper<SellerSingleBookResponseModel> getSellerSingleBookResponseModelRowMapper() {
        return new RowMapper<SellerSingleBookResponseModel>() {
            @Override
            public SellerSingleBookResponseModel mapRow(ResultSet result, int rowNum) throws SQLException {
                SellerSingleBookResponseModel model = new SellerSingleBookResponseModel();

                model.setUserDiscription(result.getString("userDescription"));

                model.setUid(result.getInt("uid"));
                model.setGenre(result.getString("genre"));
                model.setTitle(result.getString("title"));
                model.setSubtitle(result.getString("subtitle"));
                model.setAuthor(result.getString("author"));
                model.setDescription(result.getString("description"));
                model.setPicture(result.getString("picture"));
                model.setIsbn(result.getString("isbn"));
                model.setPublisher(result.getString("publisher"));
                model.setLanguage(result.getString("language"));
                model.setReleaseDate(result.getDate("releasedate"));
                model.setBinding(result.getString("binding"));
                model.setPrice(result.getFloat("price"));

                return model;
            }
        };
    }
}

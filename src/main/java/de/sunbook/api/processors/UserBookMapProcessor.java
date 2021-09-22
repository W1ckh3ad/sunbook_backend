package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.responsemodels.BookResponseModel;
import de.sunbook.api.models.responsemodels.UserBookModelForBookResponseSingleModel;
import de.sunbook.api.models.tablemodels.UserBookMapModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.UserBookMapSqlStringBuilder;

/*
This is the processor for the sql methods for the user and book mapping
*/
@Service
public class UserBookMapProcessor extends Processor {
    UserBookMapSqlStringBuilder sqlStringBuilder;

    public UserBookMapProcessor() {
        super("UserMapBook");
        sqlStringBuilder = new UserBookMapSqlStringBuilder();
    }

    public void delete(int uid, int userId) throws SQLException {
        var sql = sqlStringBuilder.delete(uid, userId);
        connection.execute(sql);
    }

    public List<UserBookMapModel> select() throws SQLException {
        var sql = sqlStringBuilder.select();
        return connection.query(sql, CustomRowMapper.GetUserBookMapModelRowMapper());
    }

    public UserBookMapModel select(int uid, int userId) throws SQLException {
        var sql = sqlStringBuilder.select(uid, userId);
        return connection.querySingle(sql, CustomRowMapper.GetUserBookMapModelRowMapper());
    }

    public int insert(UserBookMapModel model) throws SQLException {
        var sql = sqlStringBuilder.insert(model);
        return connection.insertAndGetId(sql);
    }

    public void update(UserBookMapModel model) throws SQLException {
        var sql = sqlStringBuilder.update(model);
        connection.execute(sql);
    }

    public List<BookResponseModel> select(BookQueryModel model) throws SQLException {
        var sql = sqlStringBuilder.select(model);
        return connection.query(sql, CustomRowMapper.GetBookResponseModelRowMapper());
    }

    public List<BookResponseModel> selectBooksForUserName(String username) throws SQLException {
        var sql = sqlStringBuilder.selectWithUsername(username);
        return connection.query(sql, CustomRowMapper.GetUserBookMapRowMapperWithoutUser());
    }

    public List<BookResponseModel> selectBooksForUserId(int id) throws SQLException {
        var sql = sqlStringBuilder.selectWithUserId(id);
        return connection.query(sql, CustomRowMapper.GetUserBookMapRowMapperWithoutUser());
    }

    public BookResponseModel selectBookForUserId(int id, int bookId) throws SQLException {
        var sql = sqlStringBuilder.selectWithUserIdAndBookId(id, bookId);
        return connection.querySingle(sql, CustomRowMapper.GetUserBookMapRowMapperWithoutUser());
    }

    public List<UserBookModelForBookResponseSingleModel> selectShopsForBook(int id) throws SQLException {
        var sql = sqlStringBuilder.selectWithBook(id);
        return connection.query(sql, CustomRowMapper.GetUserBookModelForBookResponseSingleModelRowMapp());
    }

}

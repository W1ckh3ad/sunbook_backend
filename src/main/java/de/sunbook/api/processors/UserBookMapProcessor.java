package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.responsemodels.BookResponseModel;
import de.sunbook.api.models.responsemodels.UserBookModelForBookResponseSingleModel;
import de.sunbook.api.models.tablemodels.UserBookMapModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.processors.interfaces.ICrudProcessor;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.UserBookMapSqlStringBuilder;

@Service
public class UserBookMapProcessor extends Processor implements ICrudProcessor<UserBookMapModel> {
    UserBookMapSqlStringBuilder sqlStringBuilder;

    public UserBookMapProcessor() {
        super("UserMapBook");
        sqlStringBuilder = new UserBookMapSqlStringBuilder();
    }

    @Override
    public void delete(int uid) throws SQLException {
        var sql = sqlStringBuilder.delete(String.valueOf(uid));
        connection.execute(sql);
    }

    @Override
    public List<UserBookMapModel> get() throws SQLException {
        return null;
    }

    @Override
    public UserBookMapModel get(int uid) throws SQLException {
        return null;
    }

    @Override
    public void post(UserBookMapModel model) throws SQLException {
        return;
    }

    @Override
    public void put(UserBookMapModel model) throws SQLException {

    }

    public List<BookResponseModel> get(BookQueryModel model) throws SQLException {
        var sql = sqlStringBuilder.select(model);
        return connection.query(sql, CustomRowMapper.GetUserBookMapRowMapper());
    }

    public List<BookResponseModel> getBooksForUserName(String username) throws SQLException {
        var sql = sqlStringBuilder.selectWithUsername(username);
        return connection.query(sql, CustomRowMapper.GetUserBookMapRowMapperWithoutUser());
    }

    public List<BookResponseModel> getBooksForUserId(int id) throws SQLException {
        var sql = sqlStringBuilder.selectWithUserId(id);
        return connection.query(sql, CustomRowMapper.GetUserBookMapRowMapperWithoutUser());
    }

    public List<UserBookModelForBookResponseSingleModel> getShopsForBook(int id) throws SQLException {
        var sql = sqlStringBuilder.selectWithBook(id);
        return connection.query(sql, CustomRowMapper.GetUserBookModelForBookResponseSingleModelRowMapp());
    }

}

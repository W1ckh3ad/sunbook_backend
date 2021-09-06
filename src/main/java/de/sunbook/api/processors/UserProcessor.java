package de.sunbook.api.processors;

import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.processors.interfaces.ICrudProcessor;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.UserSqlStringBuilder;

import java.sql.SQLException;
import java.util.List;

import de.sunbook.api.models.UserModel;

public class UserProcessor extends Processor implements ICrudProcessor<UserModel> {
    private UserSqlStringBuilder sqlStringBuilder;

    public UserProcessor() {
        super("USERS");
        sqlStringBuilder = new UserSqlStringBuilder(table, "userId");
    }

    @Override
    public void Delete(int uid) throws SQLException {
        String sql = sqlStringBuilder.delete(String.valueOf(uid));
        connection.execute(sql);
    }

    @Override
    public List<UserModel> Get() throws SQLException {
        String sql = sqlStringBuilder.select();
        return connection.query(sql, CustomRowMapper.GetUserMapper());
    }

    @Override
    public UserModel Get(int uid) throws SQLException {
        String sql = sqlStringBuilder.select(String.valueOf(uid));
        return connection.querySingle(sql, CustomRowMapper.GetUserMapper());
    }

    @Override
    public int Post(UserModel model) throws SQLException {
        String sql = sqlStringBuilder.insert(model);
        return connection.insertAndGetId(sql);
    }

    @Override
    public void Put(UserModel model) throws SQLException {
        String sql = sqlStringBuilder.update(model);
        connection.execute(sql);
    }
}

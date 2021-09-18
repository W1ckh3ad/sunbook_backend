package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.tablemodels.UserModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.processors.interfaces.ICrudProcessor;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.UserSqlStringBuilder;

@Service
public class UserProcessor extends Processor implements ICrudProcessor<UserModel> {
    private UserSqlStringBuilder sqlStringBuilder;

    public UserProcessor() {
        super("USERS");
        sqlStringBuilder = new UserSqlStringBuilder(table, "userId");
    }

    @Override
    public void delete(int uid) throws SQLException {
        String sql = sqlStringBuilder.delete(uid);
        connection.execute(sql);
    }

    @Override
    public List<UserModel> select() throws SQLException {
        String sql = sqlStringBuilder.select();
        return connection.query(sql, CustomRowMapper.GetUserMapper());
    }

    @Override
    public UserModel select(int uid) throws SQLException {
        String sql = sqlStringBuilder.select(uid);
        return connection.querySingle(sql, CustomRowMapper.GetUserMapper());
    }

    public void Register(UserModel model) throws SQLException {
        String sql = sqlStringBuilder.insert(model);
        connection.execute(sql);
    }

    @Override
    public int insert(UserModel model) throws SQLException {
        String sql = sqlStringBuilder.insertAll(model);
        return connection.insertAndGetId(sql);
    }

    @Override
    public void update(UserModel model) throws SQLException {
        String sql = sqlStringBuilder.update(model);
        connection.execute(sql);
    }

    public UserModel selectUsername(String usernameOrEmail) throws SQLException {
        String sql = sqlStringBuilder.getUsername(usernameOrEmail);
        return connection.querySingle(sql, CustomRowMapper.GetUserMapper());
    }

    public UserModel selectSeller(int id) throws SQLException {
        String sql = sqlStringBuilder.select(id) + " AND `role` IN ('admin', 'seller')";
        return connection.querySingle(sql, CustomRowMapper.GetUserMapper());
    }

    public List<UserModel> selectSeller() throws SQLException {
        String sql = sqlStringBuilder.select() + " AND `role` IN ('admin', 'seller')";
        return connection.query(sql, CustomRowMapper.GetUserMapper());
    }
}

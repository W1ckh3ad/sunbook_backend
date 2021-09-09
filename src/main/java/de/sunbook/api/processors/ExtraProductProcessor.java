package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.tablemodels.ExtraProductModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.processors.interfaces.ICrudProcessor;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.ExtraProductSqlStringBuilder;

@Service
public class ExtraProductProcessor extends Processor implements ICrudProcessor<ExtraProductModel> {
    ExtraProductSqlStringBuilder sqlStringBuilder;

    public ExtraProductProcessor() {
        super("ExtraProduct");
        sqlStringBuilder = new ExtraProductSqlStringBuilder(table);
    }

    @Override
    public void delete(int uid) throws SQLException {
        return;

    }

    @Override
    public int insert(ExtraProductModel model) throws SQLException {
        var sql = sqlStringBuilder.insert(model);
        return connection.insertAndGetId(sql);
    }

    @Override
    public List<ExtraProductModel> select() throws SQLException {
        var sql = sqlStringBuilder.select();
        return connection.query(sql, CustomRowMapper.GetExtraProductRowMapper());
    }

    @Override
    public ExtraProductModel select(int uid) throws SQLException {
        var sql = sqlStringBuilder.select(uid);
        return connection.querySingle(sql, CustomRowMapper.GetExtraProductRowMapper());
    }

    public ExtraProductModel select(String code) throws SQLException {
        var sql = sqlStringBuilder.select(code);
        return connection.querySingle(sql, CustomRowMapper.GetExtraProductRowMapper());
    }

    @Override
    public void update(ExtraProductModel model) throws SQLException {
        var sql = sqlStringBuilder.update(model);
        connection.execute(sql);
    }

}

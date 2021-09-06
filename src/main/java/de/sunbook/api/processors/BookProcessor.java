package de.sunbook.api.processors;

import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.processors.interfaces.ICrudProcessor;
import de.sunbook.api.models.BookModel;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.BookSqlStringBuilder;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookProcessor extends Processor implements ICrudProcessor<BookModel> {
    private BookSqlStringBuilder sqlStringBuilder;

    public BookProcessor() {
        super("books");
        sqlStringBuilder = new BookSqlStringBuilder(table);
    }

    @Override
    public void Delete(int uid) throws SQLException {
        String sql = sqlStringBuilder.delete(String.valueOf(uid));
        connection.execute(sql);
    }

    @Override
    public List<BookModel> Get() throws SQLException {
        String sql = sqlStringBuilder.select();
        return connection.query(sql, CustomRowMapper.GetBookMapper());
    }

    @Override
    public BookModel Get(int uid) throws SQLException {
        String sql = sqlStringBuilder.select(String.valueOf(uid));
        return connection.querySingle(sql, CustomRowMapper.GetBookMapper());
    }

    @Override
    public int Post(BookModel model) throws SQLException {
        String sql = sqlStringBuilder.insert(model);
        return connection.insertAndGetId(sql);

    }

    @Override
    public void Put(BookModel model) throws SQLException {
        String sql = sqlStringBuilder.update(model);
        connection.execute(sql);
    }
}

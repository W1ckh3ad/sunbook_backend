package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.requestmodels.BookQueryModel;
import de.sunbook.api.models.tablemodels.BookModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.processors.interfaces.ICrudProcessor;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.BookSqlStringBuilder;

@Service
public class BookProcessor extends Processor implements ICrudProcessor<BookModel> {
    private BookSqlStringBuilder sqlStringBuilder;

    public BookProcessor() {
        super("BOOK");
        sqlStringBuilder = new BookSqlStringBuilder(table);
    }

    @Override
    public void delete(int uid) throws SQLException {
        String sql = sqlStringBuilder.delete(uid);
        connection.execute(sql);
    }

    @Override
    public List<BookModel> select() throws SQLException {
        String sql = sqlStringBuilder.select();
        return connection.query(sql, CustomRowMapper.GetBookMapper());
    }

    @Override
    public BookModel select(int uid) throws SQLException {
        String sql = sqlStringBuilder.select(uid);
        return connection.querySingle(sql, CustomRowMapper.GetBookMapper());
    }

    @Override
    public int insert(BookModel model) throws SQLException {
        String sql = sqlStringBuilder.insert(model);
        return connection.insertAndGetId(sql);

    }

    @Override
    public void update(BookModel model) throws SQLException {
        String sql = sqlStringBuilder.update(model);
        connection.execute(sql);
    }

    public List<BookModel> select(BookQueryModel model) throws SQLException {
        String sql = sqlStringBuilder.select(model);
        return connection.query(sql, CustomRowMapper.GetBookMapper());
    }

    public BookModel select(String isbn) throws SQLException {
        String sql = sqlStringBuilder.selectIsbn(isbn);
        return connection.querySingle(sql, CustomRowMapper.GetBookMapper());
    }
}

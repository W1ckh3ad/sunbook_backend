package de.sunbook.api.processors.interfaces;

import java.sql.SQLException;
import java.util.List;

/*
This is an interface with for sql methods
*/
public interface ICrudProcessor<TTableModel> {
    List<TTableModel> select() throws SQLException;

    TTableModel select(int uid) throws SQLException;

    int insert(TTableModel model) throws SQLException;

    void update(TTableModel model) throws SQLException;

    void delete(int uid) throws SQLException;
}

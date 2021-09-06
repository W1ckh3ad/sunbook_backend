package de.sunbook.api.processors.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ICrudProcessor<TTableModel> {
    List<TTableModel> Get() throws SQLException;

    TTableModel Get(int uid) throws SQLException;

    int Post(TTableModel model) throws SQLException;

    void Put(TTableModel model) throws SQLException;

    void Delete(int uid) throws SQLException;
}

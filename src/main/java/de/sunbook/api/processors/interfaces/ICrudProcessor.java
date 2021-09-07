package de.sunbook.api.processors.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ICrudProcessor<TTableModel> {
    List<TTableModel> get() throws SQLException;

    TTableModel get(int uid) throws SQLException;

    void post(TTableModel model) throws SQLException;

    void put(TTableModel model) throws SQLException;

    void delete(int uid) throws SQLException;
}

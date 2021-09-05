package de.sunbook.api.connections;

import com.mysql.jdbc.Driver;

import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SqlServerConnection {
    private static SqlServerConnection instance;

    private String connectionString = "jdbc:mysql://triutils.de:33306/demo-database";
    private String user = "user";
    private String password = "S4ZVG&*?Pg6ye35d";

    private SqlServerConnection() {

    }

    public static SqlServerConnection getInstance() {
        if (instance == null) {
            instance = new SqlServerConnection();
        }
        return instance;
    }

    private JdbcTemplate getConnection() throws SQLException {
        Driver driver = new Driver();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource(driver, connectionString, user, password);
        return new JdbcTemplate(dataSource);
    }

    public void execute(String sql) throws SQLException {
        JdbcTemplate con = getConnection();
        con.execute(sql);
    }

    public int insertAndGetId(String sql) throws SQLException {
        JdbcTemplate con = getConnection();
        con.execute(sql);
        return 1;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {
        JdbcTemplate con = getConnection();
        return con.query(sql, rowMapper);
    }

    public <T> T querySingle(String sql, RowMapper<T> rowMapper) throws SQLException {
        JdbcTemplate con = getConnection();
        List<T> result = con.query(sql, rowMapper);
        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
}

package de.sunbook.api.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.mysql.jdbc.Driver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/*
This class enables the connection to the database, stores the credentials for the db
*/
public class SqlServerConnection {
    private static SqlServerConnection instance;

    private String connectionString = "jdbc:mysql://triutils.de:33306/demo-database";
    private String user = "user";
    private String password = "S4ZVG&*?Pg6ye35d";

    private SqlServerConnection() {

    }

    //get the Instance, Singleton Pattern
    public static SqlServerConnection getInstance() {
        if (instance == null) {
            instance = new SqlServerConnection();
        }
        return instance;
    }

    //get the Connection
    private JdbcTemplate getConnection() throws SQLException {
        Driver driver = new Driver();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource(driver, connectionString, user, password);
        return new JdbcTemplate(dataSource);
    }

    private Connection getConnectionNew() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException Occured...");
        } catch (SQLException e) {
            System.out.println("SQLException Occured...");
        }
        return connection;
    }

    public void execute(String sql) throws SQLException {
        JdbcTemplate con = getConnection();
        con.execute(sql);
    }

    public int insertAndGetId(String sql) throws SQLException {
        Connection connection = getConnectionNew();
        PreparedStatement statement = null;
        ResultSet rs = null;
        SQLException error = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Auto Generated Primary Key " + id);
                return id;
            }
        } catch (SQLException e) {
            System.out.println("SQLException Occured..");
            error = e;
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // close result set
                }
                if (statement != null) {
                    statement.close(); // close statement
                }
                if (connection != null) {
                    connection.close(); // close connection
                }
            } catch (SQLException e) {
                System.out.println("SQLException Occured..");
                throw e;
            }
        }
        throw error;
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

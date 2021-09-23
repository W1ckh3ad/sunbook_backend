package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.tablemodels.OrderModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.OrderSqlStringBuilder;

/*
This is the processor for the sql methods for the orders
*/
@Service
public class OrderProcesor extends Processor {

    private OrderSqlStringBuilder sqlStringBuilder;

    public OrderProcesor() {
        super("ORDERS");
        sqlStringBuilder = new OrderSqlStringBuilder(table);
    }

    public int insert(OrderModel model) throws SQLException {
        var sql = sqlStringBuilder.insert(model);
        return connection.insertAndGetId(sql);
    }

    public List<OrderModel> select() throws SQLException {
        var sql = sqlStringBuilder.select();
        return connection.query(sql, CustomRowMapper.GetOrderModelRowMapper());
    }

    public OrderModel select(int uid) throws SQLException {
        var sql = sqlStringBuilder.select(uid);
        return connection.querySingle(sql, CustomRowMapper.GetOrderModelRowMapper());
    }

    public List<OrderModel> selectUserId(int id) throws SQLException {
        var sql = sqlStringBuilder.selectUserId(id);
        return connection.query(sql, CustomRowMapper.GetOrderModelRowMapper());
    }

}

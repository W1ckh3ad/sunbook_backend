package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.tablemodels.OrderPartModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.utils.CustomRowMapper;
import de.sunbook.api.utils.sqlstringbuilder.OrderPartSqlStringBuilder;

/*
This is the processor for the sql methods for the order parts
*/
@Service
public class OrderPartProcessor extends Processor {

    OrderPartSqlStringBuilder sqlStringBuilder;

    public OrderPartProcessor() {
        super("ORDERS_PARTS");
        sqlStringBuilder = new OrderPartSqlStringBuilder(table);
    }

    public int insert(OrderPartModel model) throws SQLException {
        return connection.insertAndGetId(sqlStringBuilder.insert(model));
    }

    public List<OrderPartModel> selectOrderId(int orderId) throws SQLException {
        var sql = sqlStringBuilder.selectOrderId(orderId);
        return connection.query(sql, CustomRowMapper.getOrderPartModelRowMapper());
    }

}

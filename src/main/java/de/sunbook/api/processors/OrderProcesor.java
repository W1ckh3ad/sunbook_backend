package de.sunbook.api.processors;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.sunbook.api.models.responsemodels.OrderResponseModel;
import de.sunbook.api.models.tablemodels.OrderModel;
import de.sunbook.api.processors.abstracts.Processor;
import de.sunbook.api.utils.sqlstringbuilder.OrderSqlStringBuilder;

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

    public List<OrderResponseModel> select() throws SQLException {
        return null;
    }

    public OrderResponseModel select(int uid) throws SQLException {
        return null;
    }

}

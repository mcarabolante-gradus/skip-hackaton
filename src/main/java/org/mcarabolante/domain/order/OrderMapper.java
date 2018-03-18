package org.mcarabolante.domain.order;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.mcarabolante.commons.jdbi.MapperUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    private OrderItemMapper orderItemMapper = new OrderItemMapper();

    @Override
    public Order map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Order(
                rs.getLong("o.id"),
                rs.getLong("o.customer_id"),
                rs.getString("o.delivery_address"),
                rs.getString("o.contact"),
                rs.getLong("o.store_id"),
                OrderStatus.valueOf(rs.getString("o.status")),
                rs.getTimestamp("o.last_update").toInstant(),
                MapperUtil.fold(rs, ctx, "o.id" , orderItemMapper)
        );
    }
}

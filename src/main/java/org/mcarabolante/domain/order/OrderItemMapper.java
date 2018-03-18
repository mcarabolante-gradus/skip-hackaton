package org.mcarabolante.domain.order;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.mcarabolante.domain.product.ProductMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper implements RowMapper<OrderItem> {
    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public OrderItem map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new OrderItem(
                rs.getLong("oi.id"),
                rs.getLong("o.id"),
                rs.getLong("p.id"),
                productMapper.map(rs, ctx),
                rs.getBigDecimal("oi.price"),
                rs.getBigDecimal("oi.quantity")
        );
    }
}

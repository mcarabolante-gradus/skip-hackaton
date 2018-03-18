package org.mcarabolante.domain.product;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Product(
                rs.getLong("p.id"),
                rs.getLong("p.store_id"),
                rs.getString("p.name"),
                rs.getString("p.description"),
                rs.getBigDecimal("p.price")
        );
    }
}

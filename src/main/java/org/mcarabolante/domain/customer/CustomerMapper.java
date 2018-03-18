package org.mcarabolante.domain.customer;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Customer(
                rs.getLong("cm.id"),
                rs.getString("cm.email"),
                rs.getString("cm.name"),
                rs.getString("cm.address"),
                rs.getTimestamp("cm.created_at").toInstant()
                );
    }
}

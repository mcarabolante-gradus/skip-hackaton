package org.mcarabolante.domain.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreMapper implements RowMapper<Store> {
    @Override
    public Store map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Store(rs.getLong("s.id"),
                rs.getString("s.name"),
                rs.getString("s.address"),
                rs.getLong("s.cuisine_id")
        );
    }
}

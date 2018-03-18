package org.mcarabolante.domain.cuisine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CuisineMapper implements RowMapper<Cuisine> {
    @Override
    public Cuisine map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Cuisine(rs.getLong("c.id"), rs.getString("c.name"));
    }
}

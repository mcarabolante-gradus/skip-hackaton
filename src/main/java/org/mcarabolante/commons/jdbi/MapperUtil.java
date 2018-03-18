package org.mcarabolante.commons.jdbi;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapperUtil {

    public static <T> List<T> fold(ResultSet rs, StatementContext ctx, String columnId, RowMapper<T> mapper) throws SQLException {
        long theId = rs.getLong(columnId);
        List<T> list = new ArrayList<>();

        boolean wasSame = false;
        while (!rs.isAfterLast() && theId == rs.getLong(columnId)) {
            wasSame = true;
            list.add(mapper.map(rs, ctx));
            rs.next();
        }
        if (!rs.isAfterLast() && wasSame) {
            rs.previous();
        }
        return list;
    }
}

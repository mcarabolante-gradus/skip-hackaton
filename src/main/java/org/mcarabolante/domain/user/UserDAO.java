package org.mcarabolante.domain.user;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterRowMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("select 2")
    long select();
}

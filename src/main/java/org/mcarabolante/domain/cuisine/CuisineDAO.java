package org.mcarabolante.domain.cuisine;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterRowMapper(CuisineMapper.class)
public interface CuisineDAO {
    @SqlQuery("SELECT c.* FROM cuisine c")
    List<Cuisine> list();

    @SqlQuery("SELECT c.* " +
            "FROM cuisine c " +
            "where c.name LIKE :name")
    List<Cuisine> listLikeName(@Bind("name") String searchText);
}

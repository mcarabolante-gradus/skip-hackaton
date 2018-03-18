package org.mcarabolante.domain.store;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(StoreMapper.class)
public interface StoreDAO {

    @SqlQuery("SELECT s.* FROM store s")
    List<Store> list();

    @SqlQuery("SELECT s.* " +
            "FROM store s " +
            "WHERE s.name LIKE '%name%'")
    List<Store> listLikeName(@Bind("name") String searchText);

    @SqlQuery("SELECT s.* FROM store s " +
            "where s.id = :storeId")
    Optional<Store> findById(@Bind("storeId") Long storeId);

    @SqlQuery("SELECT s.* FROM store s " +
            "where s.cuisine_id = :cousineId")
    List<Store> listByCuisine(@Bind("cousineId") Long cousineId);

    @SqlQuery("SELECT s.* FROM store s " +
            "where s.id IN (<ids>)")
    List<Store> findByIds(@BindList("ids") List<Long> productIds);
}

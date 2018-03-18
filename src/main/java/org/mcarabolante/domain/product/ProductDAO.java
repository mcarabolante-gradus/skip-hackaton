package org.mcarabolante.domain.product;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(ProductMapper.class)
public interface ProductDAO {

    @SqlQuery("SELECT p.* " +
            "FROM product p " +
            "WHERE p.store_id = :storeId")
    List<Product> listByStore(@Bind("storeId") Integer storeId);

    @SqlQuery("SELECT p.* FROM product p ")
    List<Product> list();

    @SqlQuery("SELECT p.* " +
            "FROM product p " +
            "WHERE p.name LIKE :text " +
            "OR p.description LIKE :text")
    List<Product> listLikeName(@Bind("text") String searchText);

    @SqlQuery("SELECT p.* " +
            "FROM product p " +
            "WHERE p.id = :id")
    Optional<Product> findById(@Bind("id") Long id);
}

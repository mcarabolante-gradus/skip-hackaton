package org.mcarabolante.domain.session;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SessionDAO {

    @SqlUpdate("INSERT INTO session (token, customer_id) " +
            "VALUES (:token, :customerId) ")
    void insert(@Bind("customerId") Long customerId, @Bind("token") String token);


}

package org.mcarabolante.domain.customer;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.mcarabolante.domain.user.User;

import java.util.Optional;

@RegisterRowMapper(CustomerMapper.class)
public interface CustomerDAO {


    @SqlQuery("SELECT cm.* FROM customer cm " +
            "WHERE cm.email = :email")
    Optional<Customer> findByEmail(@Bind("email") String email);

    default Long save(CustomerCommand customer){
        if(customer.isInsert()){
            return __save(customer);
        }else {
            __update(customer);
            return customer.getId();
        }
    }

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO customer (email, name, address, password) " +
            "VALUES (:customer.email, :customer.name, :customer.address, :customer.password)")
    Long __save(@BindBean("customer") CustomerCommand customer);

    @SqlUpdate("UPDATE customer " +
            "SET email = :customer.email, name = :customer.name, address = :customer.address, password = :customer.password " +
            "WHERE id = :customer.id ")
    void  __update(@BindBean("customer") CustomerCommand customer);

    @SqlQuery("SELECT cm.* FROM customer cm " +
            "WHERE cm.id = :id")
    Customer findById(@Bind("id") Long id);

    @SqlQuery("SELECT cm.* " +
            "FROM customer cm " +
            "JOIN session s ON s.customer_id = cm.id " +
            "WHERE s.token = :token")
    Optional<Customer> findForToken(@Bind("token") String token);

    @SqlQuery("SELECT cm.password FROM customer cm WHERE cm.id = :id")
    String findPasswordById(@Bind("id") Long id);
}

package org.mcarabolante.domain.order;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.mcarabolante.domain.customer.Customer;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(OrderMapper.class)
public interface OrderDAO {

    @SqlQuery("SELECT o.*, oi.*, p.* " +
            "FROM orders o " +
            "JOIN order_item oi ON oi.order_id = o.id " +
            "JOIN product p ON p.id = oi.product_id " +
            "WHERE o.id = :orderId " +
            "ORDER BY o.id")
    Optional<Order> findById(@Bind("orderId") Long orderId);

    @Transaction
    default Long insert(Order order, Customer customer){
        Long orderId = __insert(order, customer.getId(), OrderStatus.OPEN);
        __insertItems(orderId, order.getOrderItems());

        return orderId;
    }

    @SqlBatch("INSERT INTO order_item (order_id, product_id, price, quantity) " +
            "VALUES (:orderId, :item.productId, :item.price, :item.quantity)") // TODO - do not trust user input !
    void __insertItems(@Bind("orderId") Long orderId, @BindBean("item") List<OrderItem> orderItems);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO orders(customer_id, delivery_address, contact, store_id, status) " +
            "VALUES (:customerId, :order.deliveryAddress, :order.contact, :order.storeId, :status)")
    Long __insert(@BindBean("order") Order order, @Bind("customerId") Long customerId, @Bind("status") OrderStatus status);
}

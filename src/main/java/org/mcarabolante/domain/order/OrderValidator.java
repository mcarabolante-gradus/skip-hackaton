package org.mcarabolante.domain.order;

import com.google.common.base.Predicates;
import org.mcarabolante.domain.store.Store;
import org.mcarabolante.domain.store.StoreDAO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.not;

public class OrderValidator {
    private final StoreDAO storeDAO;

    public OrderValidator(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public String isValid(Order order) {
        List<String> validations = Arrays.asList(
                validateAtLeastOneItem(order),
                validateSameStore(order)
        );
        return validations.stream()
                .filter(not(String::isEmpty))
                .collect(Collectors.joining(";"));
    }

    private String validateAtLeastOneItem(Order order) {
        if(order.getOrderItems().isEmpty()){
            return "At least one item must be ordered";
        }
        return "";
    }

    private String validateSameStore(Order order) {
        List<Long> productIds = order.getOrderItems().stream()
                .map(OrderItem::getProductId)
                .distinct()
                .collect(Collectors.toList());

        List<Store> stores = storeDAO.findByIds(productIds);

        if(stores.stream().anyMatch(store -> !store.getId().equals(order.getStoreId()))){
            return "All items must be from the same store";
        }
        return "";
    }

}

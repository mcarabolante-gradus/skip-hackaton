package org.mcarabolante.domain.order;

import org.junit.Before;
import org.junit.Test;
import org.mcarabolante.domain.store.Store;
import org.mcarabolante.domain.store.StoreDAO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static java.math.BigDecimal.ONE;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mcarabolante.domain.order.OrderStatus.OPEN;
import static org.mockito.Mockito.when;

public class OrderValidatorTest {
    @InjectMocks
    private OrderValidator orderValidator;
    @Mock
    private StoreDAO storeDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(storeDAO.findByIds(asList(1L, 2L))).thenReturn(asList(store(1L)));
        when(storeDAO.findByIds(asList(1L, 3L))).thenReturn(asList(store(1L), store(2L)));
    }

    @Test
    public void isValidTest() {
        Order order = new Order(null, null, "", "", 1L, OPEN, null,
                asList(new OrderItem(null, null, 1L, null, ONE, ONE),
                        new OrderItem(null, null, 2L, null, ONE, ONE)));

        assertThat(orderValidator.isValid(order), is(""));
    }

    @Test
    public void isValidFailsOnZeroItemsTest() {
        Order order = new Order(null, null, "", "", 1L, OPEN, null, emptyList());

        assertThat(orderValidator.isValid(order), is("At least one item must be ordered"));
    }

    @Test
    public void isValidFailsOnDifferentStoresTest() {
        Order order = new Order(null, null, "", "", 1L, OPEN, null,
                asList(new OrderItem(null, null, 1L, null, ONE, ONE),
                        new OrderItem(null, null, 3L, null, ONE, ONE)));

        assertThat(orderValidator.isValid(order), is("All items must be from the same store"));

    }

    private Store store(Long id){
        return new Store(id, "name", "add", 1L);
    }

}
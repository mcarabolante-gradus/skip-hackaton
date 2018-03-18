package org.mcarabolante.domain.customer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerValidatorTest {
    @InjectMocks
    private CustomerValidator customerValidator;
    @Mock
    private CustomerDAO customerDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(customerDAO.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(customerDAO.findByEmail("email@email.com"))
                .thenReturn(Optional.of(new Customer(2L, "email@email.com", "", "", null)));
    }

    @Test
    public void validateEmailValidWhenNotExists() {
        CustomerCommand customer = new CustomerCommand(1L, "e@email.com", "", "", "");

        assertThat(customerValidator.validateEmail(customer), is(""));
    }

    @Test
    public void validateEmailValidWhenIdsMatch() {
        CustomerCommand customer = new CustomerCommand(2L, "email@email.com", "", "", "");

        assertThat(customerValidator.validateEmail(customer), is(""));
    }


    @Test
    public void validateEmailFailsWhenIdsNotMatch() {
        CustomerCommand customer = new CustomerCommand(1L, "email@email.com", "", "", "");

        assertThat(customerValidator.validateEmail(customer), is("There is already an account with this email!"));

    }

}
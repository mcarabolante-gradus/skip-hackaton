package org.mcarabolante.domain.customer;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class CustomerValidator {
    private final CustomerDAO customerDAO;

    @Inject
    public CustomerValidator(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public String validateEmail(CustomerCommand customer){
        Optional<Customer> customerByEmail = customerDAO.findByEmail(customer.getEmail());

        return customerByEmail
                .filter(cm -> !cm.getId().equals(customer.getId()))
                .map(cm -> "There is already an account with this email!")
                .orElse("");
    }

}

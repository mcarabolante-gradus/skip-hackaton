package org.mcarabolante.domain.customer;

import org.mcarabolante.authentication.TokenGenerator;
import org.mcarabolante.commons.BcryptUtil;
import org.mcarabolante.domain.session.SessionDAO;

import javax.ws.rs.core.Response;
import java.util.Optional;

public class CustomerService {
    private final CustomerDAO customerDAO;
    private final SessionDAO sessionDAO;

    public CustomerService(CustomerDAO customerDAO, SessionDAO sessionDAO) {
        this.customerDAO = customerDAO;
        this.sessionDAO = sessionDAO;
    }

    public String saveAndGenerateToken(CustomerCommand customerCommand) {
        Long id = customerDAO.save(customerCommand);
        Customer customer = customerDAO.findById(id);
        String token = TokenGenerator.generateToken();
        sessionDAO.insert(customer.getId(), TokenGenerator.hashCode(token).toString());
        return token;
    }

    public Optional<String> authenticate(String email, String password) {
        return customerDAO.findByEmail(email)
                .flatMap(cm -> validatePasswordGenerateToken(cm, password));
    }

    private Optional<String> validatePasswordGenerateToken(Customer customer, String password){
        String dbPassword = customerDAO.findPasswordById(customer.getId());
        if(BcryptUtil.checkPw(password, dbPassword)){
            return Optional.of(generateToken(customer));
        }

        return Optional.empty();
    }

    private String generateToken(Customer customer){
        String token = TokenGenerator.generateToken();
        sessionDAO.insert(customer.getId(), TokenGenerator.hashCode(token).toString());
        return token;
    }
}

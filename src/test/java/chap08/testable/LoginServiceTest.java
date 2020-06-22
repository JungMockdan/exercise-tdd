package test.java.chap08.testable;

import org.junit.jupiter.api.Test;
import test.java.chap08.auth.Customer;
import test.java.chap08.auth.CustomerRepository;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {
    @Test
    public void test_LoginServiceTest() throws Exception {
        CustomerRepository customerRepo = new CustomerRepository() {
            @Override
            public Customer findOne(String id) {
                return null;
            }
        };
        LoginService loginService = new LoginService(customerRepo);

    }
}
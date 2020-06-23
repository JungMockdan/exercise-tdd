package test.java.chap08;

import org.junit.jupiter.api.Test;
import main.java.tdd.chap08.auth.Customer;
import main.java.tdd.chap08.auth.CustomerRepository;
import main.java.tdd.chap08.testable.LoginService;

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
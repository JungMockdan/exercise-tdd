package test.java.chap08.testable;

import test.java.chap08.auth.Customer;
import test.java.chap08.auth.CustomerRepository;
import test.java.chap08.auth.LoginResult;
/*
* AuthUtil을 사용하지 않고, 해당 클래스내 함수를 감싼 AuthService를 사용함.
* */
public class LoginService {
    private AuthService authService = new AuthService();//AuthUtil을 사용하지 않고, 해당 클래스내 함수를 감싼 AuthService를 사용함.
    private CustomerRepository customerRepo;

    public LoginService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void setAuthService(AuthService authService) {//감싼-분리된-거 사용
        this.authService = authService;
    }

    public LoginResult login(String id, String pw) {
        int resp = authService.authenticate(id, pw);//감싼-분리된-거 사용
        if (resp == -1) return LoginResult.badAuthKey();

        if (resp == 1) {
            Customer c = customerRepo.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }

}

package main.java.tdd.chap08.nontestable;

import main.java.tdd.chap08.auth.AuthUtil;
import main.java.tdd.chap08.auth.Customer;
import main.java.tdd.chap08.auth.CustomerRepository;
import main.java.tdd.chap08.auth.LoginResult;
/*
* 외부 라이브러리를가 정적 메서드를 제공할 경우 대체할 수 없다.
*
* */
public class LoginService {
    private String authKey = "somekey";
    private CustomerRepository customerRepo;

    public LoginService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public LoginResult login(String id, String pw) {
        int resp = 0;
        boolean authorized = AuthUtil.authorize(authKey);//정적메서드 대역 대체 어려움
        if (authorized) {
            resp = AuthUtil.authenticate(id, pw);//정적메서드 대역 대체 어려움
        } else {
            resp = -1;
        }
        if (resp == -1) return LoginResult.badAuthKey();

        if (resp == 1) {
            Customer c = customerRepo.findOne(id);
            return LoginResult.authenticated(c);//정적메서드 대역 대체 어려움
        } else {
            return LoginResult.fail(resp);
        }
    }

}

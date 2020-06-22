package test.java.chap08.testable;

import test.java.chap08.auth.AuthUtil;
/*
* AuthUtil 내부의 메서드 authorize(), authenticate()는 모두 정적메서드로서 대역설정이 어렵다.
* 이경우 아래처럼 해당 메서드를 감싸는 방식을 활용한다.
*
* */
public class AuthService {
    private String authKey = "somekey";

    public int authenticate(String id, String pw) {
        boolean authorized = AuthUtil.authorize(authKey);//정적메소드
        if (authorized) {
            return AuthUtil.authenticate(id, pw);//정적메소드
        } else {
            return -1;
        }
    }
}

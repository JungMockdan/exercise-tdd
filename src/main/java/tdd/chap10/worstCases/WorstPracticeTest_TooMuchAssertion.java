package main.java.tdd.chap10.worstCases;

import main.java.tdd.chap07.EmailNotifier;
import main.java.tdd.chap07.MemoryUserRepository;
import main.java.tdd.chap07.UserRegister;
import main.java.tdd.chap07.WeakPasswordChecker;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/*
* 내부 구현은 언제든지 바뀔 수 있기 때문에
* 테스트 코드는 내부 구현보다는 실행결과를 검증해야 한다.
*
* 아래처럼 어떤 메소드의 호출여부의 검증 == 실제 검증하고자하는 기능의 검증.
* 즉, 변경 가능성으로 테스트까지 깨질수 있는 내부 검증보다는 실행결과 검증에 집증.
* 단, 피치 못할 내부검증-레거시 코드에 기능 추가등-은 필요하기도 한다.
* 이때는 모의 객체를 적절히 활용하면 좋다.
*
* */
public class WorstPracticeTest_TooMuchAssertion {

    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker
            = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeReposigoty = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @Test
    public void bad_checkPassword_tooMuchAssertion_test() throws Exception {

        userRegister.register("id", "pw", "email");
        //메서드 호출 검사
        BDDMockito.then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(Mockito.anyString());
        //메서드 호출 하지 않은 것 검사
        BDDMockito.then(fakeReposigoty)
                .should(Mockito.never())
                .findById(Mockito.anyString());
        //이후 검증 소스..... 생략

    }
}
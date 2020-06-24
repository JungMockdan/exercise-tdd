package main.java.tdd.chap10.worstCases;

import main.java.tdd.chap07.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


/*
*
* 모의 객체 설정시에 유의.
* 테스트의 목적이 모의객체 자체를 테스트함이 아니기 때문에
* 특정값을 세팅하여 테스트실패가 생기지 않도록 유념.
*
* 암호약할 때 WeakPasswordException 던지는 것을 테스트하는데
* 내가 알만할 약한암호의 테스트 없이-"약한암호의 테스트"는 이미 했다고 치던가...-...
* 약하다고 치고 WeakPasswordException이 잘 발생하는냐만 테스트 하는 경우인 것으로 추정함.
*
* 테스트 의도를 해치지 않는 범위 내에서 "Mockito.anyString()"와 같은 범용적 값을 사용할 것.
*
* */
public class WorstPracticeTest_SetMockObj {

    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker
            = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeReposigoty = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp(){
        userRegister = new UserRegister(mockPasswordChecker, fakeReposigoty,mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    public void bad_setSpecificMockObj_weakPassword_test() throws Exception {
        BDDMockito
                .given(mockPasswordChecker.checkPasswordWeak("pw"))
                .willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id","pw","email");
        });
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    public void good_setSpecificMockObj_weakPassword_test() throws Exception {
        BDDMockito
                .given(mockPasswordChecker.checkPasswordWeak(Mockito.anyString()))
                .willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id","pw","email");
        });
    }

}
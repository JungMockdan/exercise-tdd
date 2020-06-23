package test.java.chap07;

import main.java.tdd.chap07.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);
    /*
    * 위 소스 설명 - Mockito.mock(타입) : 인자로 들어온 타입의 모의객체 생성
    * */

    @BeforeEach
    void setUp(){
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository,mockEmailNotifier);
    }

    @Test
    void weakPassword(){
        //스텁설정 : mockPasswordChecker.checkPasswordWeak("pw")를 호출하면 willReturn(인자)의 인자를 리턴한다.
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, ()->{
            userRegister.register("id","pw", "email");
        });
    }

    @DisplayName("회원 가입 시 암호 검사 수행함")
    @Test
    void checkPassword(){
        userRegister.register("id","pw", "email");
        //ArgumentCaptor 는 모의 객체를 메서드를 호출할 때 전달한 객체를 담는 기능.
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());
        //BDDMockito.then(mockEmailNotifier) : 인자로 전달한 mockPasswordChecker 모의 객체의
        //.should() 특정 메소드의 호출 검증하는데...
        //.sendRegisterEmail(captor.capture()); :captor.capture()를 인자로해서 메서드를 호출 여부를 확인.
        String realEmail = captor.getValue();// captor가 담은 인자를 확인 가능 하네...
        assertEquals("email@email.com", realEmail);



    }

}

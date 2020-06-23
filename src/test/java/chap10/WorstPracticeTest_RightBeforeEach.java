package test.java.chap10;

import main.java.tdd.chap07.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 * 같은 단위(?) 내의 여러 테스트를 진행 시 소스가 중복되고,
 * 소스 중복을 위해 @BeforeEach를 이용해
 * 매 메서드의 실행전 set up 을 기술한다.
 * 다만, 해당 부분에서는 공통으로 사용되는 필드나 변수 의 initiolize만을 주로 해야 한다.
 * 값을 세팅하거나, 모의 객체를 설정하거나,
 * 상황별 셋업을 하는 것 모두 지양해야 한다.
 *
 * 테스트메서드는 검증을 목표로한 하나의 완벽한 프로그램이어야 한다.
 * 그리고, 각 메서드는 검증내용을 스스로 잘 설명해야 한다.
 * 따라서, 상황구성메서드-값설정,대역설정등-는 테스트 메서드 안에 구성하도록 한다.
 * */
public class WorstPracticeTest_RightBeforeEach {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);
    /*
     * 위 소스 설명 - Mockito.mock(타입) : 인자로 들어온 타입의 모의객체 생성
     * */

    //    @BeforeEach
    void bad_setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
        //공통의 상황설정.
        fakeRepository.save(new User("id", "pw1", "email@email.com"));
    }

    @BeforeEach
    void good_setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, mockEmailNotifier);
    }

    //이하 test 메서드 구현 내용은 생략한다.
    @Test
    void noUser() {

        assertThrows(UserNotFoundException.class, () -> {
            fakeRepository.save(new User("id", "pw1", "email@email.com"));
        });
    }


}

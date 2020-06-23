package test.java.chap10;

import main.java.tdd.chap07.MemoryUserRepository;
import main.java.tdd.chap07.SpyEmailNotifier;
import main.java.tdd.chap07.UserRegister;
import main.java.tdd.chap07.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorstPracticeTest_AssertOnlyOne {
    private UserRegister userRegister;

    /*
    * 1. 테스트 집중도가 떨어진다.
    * 2. 테스트 실패시 무엇때문에 실패했는지 확인이 필요하다.
    * ==> 하나의 테스트에 한개만 검증하도록 하는 것이 좋다.
    *
    * */
    @DisplayName("같은 ID가 없으면 가입에 성공하고 메일을 전송함.")
    @Test
    public void bad_assertTwoAtOneTime_registerAndSendEmail_test() throws Exception {
        userRegister.register("id","pw","email");

        //검증 1 : 회원 데이터가 올바르게 저장되었나?
        //검증 2 : 이메일 발송을 요청했는가?
    }

    /*
    *
    * 아래는 위 검증을
    * 2개로 나누어 테스트함.
    * ref. : test.java.chap07.UserRegisterTest
    *
    * */
    //대역
    private MemoryUserRepository fakeRepository = new  MemoryUserRepository();
    //스파이
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @DisplayName("같은 ID가 없으면 가입에 성공")
    @Test
    public void good_assertOne_noDupleThenRegisterSuccess_test() throws Exception {
        userRegister.register("id","pw","email");
        User savedUser = fakeRepository.findById("id");
        assertEquals("id",savedUser.getId());
        assertEquals("email",savedUser.getEmail());
    }
    @DisplayName("가입 성공 시 메일 발송.")
    @Test
    public void good_assertAnotherOne_registerSuccessThenEmailSend_test() throws Exception {
        userRegister.register("id","pw","email");
        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com",spyEmailNotifier.getEmail());
    }
}
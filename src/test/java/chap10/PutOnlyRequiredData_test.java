package test.java.chap10;

import main.java.tdd.chap10.user.UserRegister;
import main.java.tdd.chap10.user.DupIdException;
import main.java.tdd.chap10.user.MemoryUserRepository;
import main.java.tdd.chap10.user.RegisterReq;
import main.java.tdd.chap10.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

/*
* 아래 test에서는 id 중복만을 검사하고 있기 때문에
* 나쁜 case에서 처럼 불필요한 데이터까지 설정할 필요가 없다.
* id만 설정해도 된다.
* 좋은case를 보면 id만 설정하고 있다.
* */
public class PutOnlyRequiredData_test {
    //대역
    private MemoryUserRepository memoryRepository = new  MemoryUserRepository();
    private UserRegister userRegister;
    @DisplayName("불필요한 데이터도 넣음")
    @Test
    void bad_dupId_test(){
        memoryRepository.save(
                User.builder()
                        .id("dupid")
                        .name("이름")
                .email("abc@abc.com")
                .password("abcd")
                .regData(LocalDateTime.now())
                .build()
        );
        RegisterReq reqz = RegisterReq.builder()
                    .id("dupid")
                    .name("다른이름")
                    .email("abc@abc.com")
                    .password("abcde")
                    .regData(LocalDateTime.now())
                .build();
        assertThrows(
                DupIdException.class
                ,()->userRegister.register(reqz)
        );

    }

    @DisplayName("테스트에 필요한 값만 설정")
    void good_dupId_test(){
        //동일 ID가 존재하는 상황
        memoryRepository.save(User.Builder.id("dupid").build());

        RegisterReq req = RegisterReq.builder().id("dupid").build();

        assertThrows(DupIdException.class, ()->userRegister.register(req));
    }
}
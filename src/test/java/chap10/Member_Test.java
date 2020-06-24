package test.java.chap10;

import main.java.tdd.chap10.user.BizClock;
import main.java.tdd.chap10.user.Member;
import main.java.tdd.chap10.user.TestBizClock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class Member_Test {
    TestBizClock testClock = new TestBizClock();
    @AfterEach
    void resetClock(){
        testClock.reset();
    }

    @Test
    public void notExpired() throws Exception {
        testClock.setNow(LocalDateTime.of(2019,12,31,0,0,0));
        LocalDateTime expiry = LocalDateTime.of(2019,12,31,0,0,0);
        Member m = Member.builder().expiryDate(expiry).build();
        assertFalse(m.isExpired());
    }

    @DisplayName("시간이 지나면 날짜를 바꿔 주어야 할 수 도 있는 테스트 코드")
    @Test
    void bad_notExpired(){
        LocalDateTime expiry = LocalDateTime.of(2099,12,31,0,0,0);
        Member m = Member.builder().expiryDate(expiry).build();
        assertFalse(m.isExpired());
    }
    @DisplayName("실행시점에 상관없이 항상 통과한다.")
    @Test
    void better_expired_Only_1ms(){
        LocalDateTime expiry = LocalDateTime.of(2019,12,31,0,0,0);
        Member m = Member.builder().expiryDate(expiry).build();
        assertFalse(m.passedExpiryDate(LocalDateTime.of(2019,12,30,0,0,0,1000000)));
    }

    @Test
    void good_expired_Only_1ms(){
        testClock.setNow(LocalDateTime.of(2019,12,31,0,0,0));
        LocalDateTime expiry = LocalDateTime.of(2019,12,31,0,0,0,1000000);
        Member m = Member.builder().expiryDate(expiry).build();
        assertTrue(m.isExpired());
    }
}
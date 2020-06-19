package test.java.chap05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("@DisplayName 테스트")
public class DisplayNameTest {

    @DisplayName("값이 같은지 테스트")
    @Test
    public void test_equality_vals() throws Exception {
        assertEquals(1, 1);
    }

    @Disabled
    @DisplayName("@Disabled 어노테이션 테스트")
    @Test
    void disabledTest() {
        System.out.println("이게 출력되면 안된다.");
        System.out.println("잠깐 테스트를 실행하지 않아야 할 필요성이 있을 때 사용.");
    }
}
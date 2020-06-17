package test.java.chap05;

import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JunitTest {
    @BeforeAll
    static void setUpAll(){
        System.out.println("==========================================");
        System.out.println("===Start : JUNIT TEST : JunitTest.class===");
    }

    @Test
    public void test_JunitTest() throws Exception {
        System.out.println("\t\t@Test :1. test_JunitTest()  테스트 클래스 내 메서드 실행 순서 없음...");
    }

    @BeforeEach
    void setUp(){
        System.out.println("\t===Before Method Test===");
        System.out.println("\t\t@BeforeEach : 테스트 실행에 필요한 준비작업에 사용. 임시파일 생성, 객체 생성등.");
    }
    @AfterEach
    void tearDown(){
        System.out.println("\t\t@AfterEach : 테스트 실행 후 정리 해야 하는 것 있을 때 사용. 임시파일 삭제 등.");
        System.out.println("\t===After Method Test===");
    }

    @Test
    void assert_same_method_test(){
        System.out.println("\t\t@Test :2. assert_same_method_test()");
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.now();
        LocalDate localDate3 = localDate1;
        assertSame(localDate1,localDate3);
    }

    @Test
    void assert_throws_method_test(){

        System.out.println("\t\t@Test :3. assert_throws_method_test()");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException();
        });

        assertTrue(thrown.toString().contains("IllegalArgumentException"));

    };

    @AfterAll
    static void tearDownAll(){
        System.out.println("결과를 보면.");
        System.out.println("클래스 단위 test에 method 순서는 정해져있지 않다.");
        System.out.println("해서, 모든 test클래스 내의 메서는 독립적이어야 한다. " +
                "\n필드공유등이 있으면 안된다." +
                "\n또, 순차적으로 실행되어 서로 영향을 주고 받는 test도 안된다.");
        System.out.println("===End : JUNIT TEST : JunitTest.class===");
        System.out.println("==========================================");
    }

}

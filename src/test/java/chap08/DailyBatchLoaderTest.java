package test.java.chap08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import main.java.tdd.chap08.testable.DailyBatchLoader;
import main.java.tdd.chap08.testable.Times;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

/*
* Times의 대역을 이용하여 날짜 다름으로인한 test 결과 다름을 방지..
* Mockito를 이용하여, 고정된 값을 리턴하도록 한다.
*
* */
public class DailyBatchLoaderTest {

    private Times mockTimes = Mockito.mock(Times.class);
    private final DailyBatchLoader loader = new DailyBatchLoader();

    @BeforeEach
    void setUp(){
        loader.setBasePath("src/test/resources");
        loader.setTimes(mockTimes);
    }

    @Test
    public void loadCount() throws Exception {
        given(mockTimes.today()).willReturn(LocalDate.of(2019,1,1));//mockTimes.today() 호출시 return값을 고정.
        int ret = loader.load();// 파일의 line수를 리턴한다.
        assertEquals(3,ret);
    }
}
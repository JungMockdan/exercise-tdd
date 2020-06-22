package test.java.appendix_C;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;

public class GameGenTest {
    @DisplayName("mockito를 이용하여 스텁설정 테스트")
    @Test
    public void mockTest() throws Exception {
        //1. 모의 객체 생성
        GameNumGen genMock = mock(GameNumGen.class);
        //2. 스텁 설정.(willReturn 대신 willThrow() 메서드를 사용하여 익셉션을 발생할 수도 있다.
        given(genMock.generate(GameLevel.EASY)).willReturn("123");
        //3. 스텁 설정에 매칭되는 메서드 실행.
        String num = genMock.generate(GameLevel.EASY);
        //num2는 null이 된다. 왜냐하면 genMock.generate(GameLevel.NORMAL)에 스텁설정을 한적이 없기 때문이다.
        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals("123",num);
        assertEquals(null,num2);
    }

    @DisplayName("mockito를 이용하여 스텁설정 테스트: 스텁설정 안하면 디폴트값 리턴")
    @Test
    public void mockNotSetStubTest() throws Exception {
        //1. 모의 객체 생성
        GameNumGen genMock = mock(GameNumGen.class);
        //2. 스텁 설정.(willReturn 대신 willThrow() 메서드를 사용하여 익셉션을 발생할 수도 있다.
        given(genMock.generate(GameLevel.EASY)).willReturn("123");
        //3. 스텁 설정에 매칭되는 메서드 실행.
        //num2는 null이 된다. 왜냐하면 genMock.generate(GameLevel.NORMAL)에 스텁설정을 한적이 없기 때문이다.
        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals(null,num2);
    }

    /*
    * BDDMockito.willThrow()는 발생할 익셉션 타빙이나 익셉션 객체를 인자로 받는다.
    * 이어서 given()메서드는 모의객체를 전달받는다. 주의점은 여기서 모의객체 메서드 실행이 아닌 객체 그 자체를 받는것이다.
    *
    * */
    @DisplayName("mockito를 이용하여 스텁설정과 익셉션 발생 테스트")
    @Test
    public void throwTest() throws Exception {
        //1. 모의 객체 생성
        GameNumGen genMock = mock(GameNumGen.class);
        //2. 스텁 설정.( willThrow() 대신 willReturn메서드를 사용하여 예상되는 결과 테스트.
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,()->{
            genMock.generate(null);
        });
    }
    @DisplayName("리턴타입 void 메서드에 대해 익셉션 발생 테스트")
    @Test
    void  voidMethodWillThrowTest(){
        List<String> mockList = mock(List.class);
        willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();
        assertThrows(
                UnsupportedOperationException.class,()->{
                    mockList.clear();
                }
        );
    }
}
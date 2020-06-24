package test.java.chap10;


import main.java.tdd.chap10.game.bad.Game;
import main.java.tdd.chap10.game.bad.Score;
import main.java.tdd.chap10.game.good.GameNumGen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class Random_Test {
    @Test
    void fail_noMatch_test(){
        main.java.tdd.chap10.game.bad.Game g = new main.java.tdd.chap10.game.bad.Game();
        Score s = g.guess(1,2,3);//테스트를 통과시킬 수 있는 값을 알수 없음.
        assertEquals(0, s.strikes());
        assertEquals(0, s.balls());
    }

    @Test
    void good_noMatch_test(){

        //랜덤 값 생성을 별도 타입으로 분리하고
        // 이를 대역으로 대체해서 대체
        GameNumGen gen = mock(GameNumGen.class);
        given(gen.generate()).willReturn(new int[]{1,2,3});

        main.java.tdd.chap10.game.good.Game g = new main.java.tdd.chap10.game.good.Game(gen);
        main.java.tdd.chap10.game.good.Score s = g.guess(4,5,6);//테스트를 통과시킬 수 있는 값을 알수 없음.
        assertEquals(0, s.strikes());
        assertEquals(0, s.balls());
    }
}
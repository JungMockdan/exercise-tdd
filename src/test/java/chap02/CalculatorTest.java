package test.java.chap02;

import org.junit.jupiter.api.Test;
import main.java.tdd.chap02.Calculator;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void test_덧셈() throws Exception {
        int result = Calculator.plus(1,2);
        assertEquals(3,result);
        assertEquals(5,Calculator.plus(4,1));

    }
}
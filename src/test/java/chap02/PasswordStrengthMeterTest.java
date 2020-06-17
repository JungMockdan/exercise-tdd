package test.java.chap02;

import main.java.tdd.chap02.PasswordStrength;
import main.java.tdd.chap02.PasswordStrengthMeter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }
    @Test
    void nullInput_Then_Invaild(){
        assertStrength(null,PasswordStrength.INVALID);
    }

    @Test
    void emptyInput_then_Invalid(){
        assertStrength("",PasswordStrength.INVALID);
    }
    @Test
    void meetsAllCriteria_Then_Strong() {
        assertStrength("abc1!Add",PasswordStrength.STRONG);
        assertStrength("ab12!!@AB",PasswordStrength.STRONG);
    }


    @Test
    void meetsOtherCriteria_except_for_Length_Then_Nomal() {
        assertStrength("ab12!@A",PasswordStrength.NORMAL);
        assertStrength("Ab12!c",PasswordStrength.NORMAL);
    }

    @Test
    void meetsOtherCriteria_except_for_number_then_Normal() {
        assertStrength("ab!@ABqwer",PasswordStrength.NORMAL);
    }

    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        assertStrength("ab!@cc12er",PasswordStrength.NORMAL);
    }
    @Test
    void meetsOnlyLengthCriteria_Then_Weak(){
        assertStrength("asdfqwerwer",PasswordStrength.WEAK);
    }
    @Test
    void meetsOnlyNumCriteria_Then_Weak(){
        assertStrength("2341",PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUppCriteria_Then_Weak(){
        assertStrength("ABC",PasswordStrength.WEAK);
    }

    @Test
    void meetsNoneCriteria_Then_Weak(){
        assertStrength("ab",PasswordStrength.WEAK);
    }


}
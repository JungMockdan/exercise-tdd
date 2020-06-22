package test.java.chap08.testable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.java.chap08.subs.Grade;
import test.java.chap08.subs.Product;
import test.java.chap08.subs.Subscription;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
/*포인트계산메서드-calculatePoint-에서 계산부분만 검증하는 테스트*/
public class PointRuleTest {

    @DisplayName("만료전 GOLD등급은 130포인트")
    @Test
    public void calcBeforeExpiredGoldGradePoint() throws Exception {
        PointRule rule = new PointRule();
        Subscription s = new Subscription(
                "user",
                LocalDate.of(2019,5,5),
                Grade.GOLD);
        Product p = new Product("pid");
        p.setDefaultPoint(20);

        int point = rule.calculate(s,p, LocalDate.of(2019,5,1));

        assertEquals(130,point);
    }
}
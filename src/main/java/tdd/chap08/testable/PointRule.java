package main.java.tdd.chap08.testable;

import main.java.tdd.chap08.subs.Product;
import main.java.tdd.chap08.subs.Subscription;

import java.time.LocalDate;

import static main.java.tdd.chap08.subs.Grade.GOLD;

/*
calculatePoint() 메서드에서 포인트 계산 기능만을 테스트 하기 위해
계산 하는 부분만 따로 분리하였다.
이렇게 하지 않으면, calculatePoint()에 기술된. SubscriptionDao와 ProductDao의 완전한 동작이 필요하다.
*/
public class PointRule {
    public int calculate(Subscription s, Product p, LocalDate now) {
        int point = 0;
        if (s.isFinished(now)) {
            point += p.getDefaultPoint();
        } else {
            point += p.getDefaultPoint() + 10;
        }
        if (s.getGrade() == GOLD) {
            point += 100;
        }
        return point;
    }
}

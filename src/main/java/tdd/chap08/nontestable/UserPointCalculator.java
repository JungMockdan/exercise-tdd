package main.java.tdd.chap08.nontestable;
/*테스트하기 어려운 사례를 테스트 가능하게 바꾸어 보는 실습
 *
 * 시나리오 :
 * 1. 기능 구현이 섞여 있어 특정한 부분만 테스트하기 어려운 코드
 *
 * */
//아래 시간을 체크하는 부분은 테스트 시점에 따라 다른결과를 가져온다.
//마찬가지로 Random 같은 임의의 값을 사용하는 경우에도 값이 달라질 수 있다.

import main.java.tdd.chap08.subs.*;

import java.time.LocalDate;

import static main.java.tdd.chap08.subs.Grade.GOLD;

public class UserPointCalculator {
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao,
                               ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    /* 사용자의 구동상태 혹은 제품에 따른 계산 결과값 리턴 */
    public int calculatePoint(User u) {
        Subscription s = subscriptionDao.selectByUser(u.getId());
        if (s == null) throw new NoSubscriptionException();
        Product p = productDao.selectById(s.getProductId());
        //아래 시간을 체크하는 부분은 테스트 시점에 따라 다른결과를 가져온다.
        //마찬가지로 Random 같은 임의의 값을 사용하는 경우에도 값이 달라질 수 있다.
        LocalDate now = LocalDate.now();
        int point = 0;
        // 포인트 계산 로직만을 테스트하기 어렵다.
        // 포인트 계산 로직을 테스트하기위해  SubscriptionDao, ProductDao 두대역을 잘-알맞게-구성해야 한다.
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

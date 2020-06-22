package test.java.chap08.testable;
/*테스트하기 어려운 사례를 테스트 가능하게 바꾸어 보는 실습
 *
 * 시나리오 :
 * 1. 기능 구현이 섞여 있어 특정한 부분만 테스트하기 어려운 코드
 *
 * */
//아래 시간을 체크하는 부분은 테스트 시점에 따라 다른결과를 가져온다.
//마찬가지로 Random 같은 임의의 값을 사용하는 경우에도 값이 달라질 수 있다.

import test.java.chap08.subs.*;

import java.time.LocalDate;

import static test.java.chap08.subs.Grade.GOLD;

public class UserPointCalculator {
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;
    //기본구현을 사용
    private PointRule pointRule;

    public UserPointCalculator(SubscriptionDao subscriptionDao,
                               ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao, PointRule pointRule) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
        this.pointRule = pointRule;
    }

    public void setPointRule(PointRule pointRule) {
        this.pointRule = pointRule;
    }

    /* 사용자의 구동상태 혹은 제품에 따른 계산 결과값 리턴 */
    public int calculatePoint(User u) {
        Subscription s = subscriptionDao.selectByUser(u.getId());
        if (s == null) throw new NoSubscriptionException();
        Product p = productDao.selectById(s.getProductId());
        //아래 시간을 체크하는 부분은 테스트 시점에 따라 다른결과를 가져온다.
        //마찬가지로 Random 같은 임의의 값을 사용하는 경우에도 값이 달라질 수 있다.
        LocalDate now = LocalDate.now();
        // 계산기능을 따로 분리하였다.
        return new PointRule().calculate(s,p,now);
    }
}

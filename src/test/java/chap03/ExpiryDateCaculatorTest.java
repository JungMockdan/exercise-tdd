package test.java.chap03;

import org.junit.jupiter.api.Test;
import main.java.tdd.chap04.ExpiryDateCaculator;
import main.java.tdd.chap04.PayData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//매달 비용을 지불해야 사용할 수 있는 유료 서비스가 있다고 해보자. 이 서비스는 다음 규칙
//        에 따라 서비스 만료일을 결정한다.
//        ● 서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스
//        만료일이 된다.
//        ● 2개월 이상 요금을 납부할 수 있다.
//        ● 10만 원을 납부하면 서비스를 1년 제공한다.
public class ExpiryDateCaculatorTest {
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){

        this.assertExpiryDate(
                PayData.builder().billingDate(LocalDate.of(2020,2,3)).payAmount(10_000).build(),
                LocalDate.of(2020,3,3)
        );

        this.assertExpiryDate(
                PayData.builder().billingDate(LocalDate.of(2020,5,1)).payAmount(10_000).build(),
                LocalDate.of(2020,6,1)
        );
    }

    @Test
    void 만원_납부하면_한달_뒤_일자가_같지_않음(){

        this.assertExpiryDate(
                PayData.builder()
                        .billingDate(
                LocalDate.of(2019,1,31))
                        .payAmount(10_000).build(),
                LocalDate.of(2019,2,28)
        );
        this.assertExpiryDate(
                PayData.builder()
                        .billingDate(
                LocalDate.of(2019,5,31))
                        .payAmount(10_000).build(),
                LocalDate.of(2019,6,30)
        );
        this.assertExpiryDate(
                PayData.builder()
                        .billingDate(
                        LocalDate.of(2020,1,31))
                        .payAmount(10_000).build(),
                LocalDate.of(2020,2,29)
        );

    }

    /*첫 납부일과 만료일의 납부일자가 같지 않을 때-보통 첫납부 일이 월의 마지막 날 일때 다음달 월의 마지막 일이 같지 않는 경우임.-
    그 때는 첫 납부일을 기준으로 하여 다음 만료 일을 정함. */
    /*
    * 첫 납부일 2019-01-30 만료 2019-02-28 1만원 남부시 다음 만료일 2019-03-30이다.
    * */
    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(
                        LocalDate.of(2019,1,31))
                .billingDate(
                        LocalDate.of(2019,2,28))
                .payAmount(10_000).build();

        this.assertExpiryDate(
                payData,
                LocalDate.of(2019,3,31)
        );


        PayData payData2 = PayData.builder()
                .firstBillingDate(
                        LocalDate.of(2019,1,30))
                .billingDate(
                        LocalDate.of(2019,2,28))
                .payAmount(10_000).build();

        this.assertExpiryDate(
                payData2,
                LocalDate.of(2019,3,30)
        );

        PayData payData3 = PayData.builder()
                .firstBillingDate(
                        LocalDate.of(2019,5,31))
                .billingDate(
                        LocalDate.of(2019,6,30))
                .payAmount(10_000).build();

        this.assertExpiryDate(
                payData3,
                LocalDate.of(2019,7,31)
        );

    }
    /* 2만원 이상 남부하면 비례해서 만료일 계산*/
    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산(){
        PayData payData = PayData.builder()
                .billingDate(
                        LocalDate.of(2019,3,1))
                .payAmount(20_000).build();

        this.assertExpiryDate(
                payData,
                LocalDate.of(2019,5,1)
        );

        PayData payData2 = PayData.builder()
                .billingDate(
                        LocalDate.of(2019,3,1))
                .payAmount(30_000).build();

        this.assertExpiryDate(
                payData2,
                LocalDate.of(2019,6,1)
        );

    }
    @Test
    void 첫_납부일과_만료일_일자가_다를_때_이만원_이상_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(
                        LocalDate.of(2019,1,31))
                .billingDate(
                        LocalDate.of(2019,2,28))
                .payAmount(20_000).build();

        this.assertExpiryDate(
                payData,
                LocalDate.of(2019,4,30)
        );
    }
    @Test
    void 십만원_납부시에_1년을_제공(){
        PayData payData = PayData.builder()
                .billingDate(
                        LocalDate.of(2019,1,28))
                .payAmount(100_000).build();

        this.assertExpiryDate(
                payData,
                LocalDate.of(2020,1,28)
        );
    }

    @Test
    void 십만원_이상_납부시에_1년plus_alpha_제공(){
        PayData payData = PayData.builder()
                .billingDate(
                        LocalDate.of(2019,1,28))
                .payAmount(140_000).build();

        this.assertExpiryDate(
                payData,
                LocalDate.of(2020,5,28)
        );

        PayData payData2 = PayData.builder()
                .billingDate(
                        LocalDate.of(2019,1,28))
                .payAmount(240_000).build();

        this.assertExpiryDate(
                payData2,
                LocalDate.of(2021,5,28)
        );
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCaculator cal = new ExpiryDateCaculator();
        LocalDate calcedExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, calcedExpiryDate);
    }

}

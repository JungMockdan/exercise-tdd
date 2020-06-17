package main.java.tdd.chap04;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCaculator {

    public LocalDate calculateExpiryDate(PayData payData) {

        int addedMonths = getAddedMonths(payData);


        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {

            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private int getAddedMonths(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10_000;
        if (addedMonths >= 10) {

            if (addedMonths % 10 == 0) {
                addedMonths = 12 * (addedMonths / 10);
            }else{
                int bonusedMonth =  12 * (addedMonths / 10);
                int additionalMonth = addedMonths % 10;
                addedMonths = bonusedMonth + additionalMonth;
            }
        }
        return addedMonths;
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
            if (dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        }
        return null;
    }


}

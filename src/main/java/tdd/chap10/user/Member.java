package main.java.tdd.chap10.user;

import java.time.LocalDateTime;

public class Member {
    private LocalDateTime expiryDate;

    public static Builder builder() {
        return new Builder();
    }

    public boolean passedExpiryDate(LocalDateTime compareDate) {
        System.out.println("expiryDate :: "+this.expiryDate.toLocalDate());
        System.out.println("compareDate :: "+compareDate.toLocalDate());
//        this.expiryDate = expiryDate;
        System.out.println("now :: " + BizClock.now().toLocalDate());

        return this.expiryDate.isBefore(compareDate);
    }

    public static class Builder {
        private Member data = new Member();

        public Builder expiryDate(LocalDateTime expiryDate) {
            data.expiryDate = expiryDate;
            return this;
        }
        public Member build() {
            return data;
        }
    }
    public boolean isExpired(){
        System.out.println("expiryDate :: "+expiryDate.toLocalDate());
        System.out.println("now :: " + BizClock.now().toLocalDate());
        return expiryDate.isBefore(BizClock.now());
    }

}

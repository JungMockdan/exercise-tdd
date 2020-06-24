package main.java.tdd.chap10.user;

import java.time.LocalDateTime;

public class TestBizClock extends BizClock{

    private LocalDateTime now;

    public TestBizClock() {
        setInstance(this);
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    @Override
    protected LocalDateTime timeNow() {
        return super.timeNow();
    }
}

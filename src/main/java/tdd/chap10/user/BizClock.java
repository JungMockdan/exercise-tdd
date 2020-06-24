package main.java.tdd.chap10.user;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.time.LocalDateTime;

public class BizClock {
    private static BizClock DEFAULT = new BizClock();
    private static BizClock instance = DEFAULT;

    public static void reset() {
        instance = DEFAULT;
    }

    public static LocalDateTime now() {
        return instance.timeNow();
    }

     protected void setInstance(BizClock bizClock){
        BizClock.instance = bizClock;
    }

    protected LocalDateTime timeNow() {
        return LocalDateTime.now();
    }


}

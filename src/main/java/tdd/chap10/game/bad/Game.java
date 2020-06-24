package main.java.tdd.chap10.game.bad;

import java.util.Random;
public class Game{


    private final int[] nums;

    public Game() {
        Random random = new Random();
        int firstNo = random.nextInt(10);
        int secondNo = random.nextInt(10);
        int thirdNo = random.nextInt(10);
        this.nums = new int[]{firstNo,secondNo,thirdNo};
    }

    public Score guess(int gs1, int gs2, int gs3) {
        return null;
    }
}


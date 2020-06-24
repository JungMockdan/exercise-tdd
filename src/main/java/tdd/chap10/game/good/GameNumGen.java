package main.java.tdd.chap10.game.good;

import java.util.Random;

public class GameNumGen {
    public int[] generate(){
        int[] nums = new int[3];
        Random random = new Random();
        int firstNo = random.nextInt(10);
        int secondNo = random.nextInt(10);
        int thirdNo = random.nextInt(10);
        return nums;
    }
}

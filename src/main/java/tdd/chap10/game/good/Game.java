package main.java.tdd.chap10.game.good;

import java.util.Random;

public class Game{


    private final int[] nums;

    public Game(GameNumGen gen) {
        this.nums = gen.generate();
    }



    public Score guess(int gs1, int gs2, int gs3) {
        return null;
    }
}


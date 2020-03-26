package com.example.starways.layer_game.engine.mathematics;

import java.util.Random;

public class EngineRandom {

    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

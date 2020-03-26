package com.example.starways.layer_game.media.sounds;

import android.media.SoundPool;

public class GameSoundPlayer {

    public static void playExplosions(int id, int[] soundsExplosions, SoundPool explosions) {
        explosions.play(soundsExplosions[id], 1, 1, 0, 0, 1);
    }

    public static void playLaserFires(int id, int[] soundsLaserFires, SoundPool laserFires) {
        laserFires.play(soundsLaserFires[id], 1, 1, 0, 0, 1);
    }

    public static void playWin(int[] soundsRest, SoundPool rest) {
        rest.play(soundsRest[SoundIDs.WIN], 1, 1, 0, 0, 1);
    }

    public static void playLose(int[] soundsRest, SoundPool rest) {
        rest.play(soundsRest[SoundIDs.LOSE], 1, 1, 0, 0, 1);
    }

    public static void playPortal(int[] soundsRest, SoundPool rest) {
        rest.play(soundsRest[SoundIDs.PORTAL_0], 1, 1, 0, 0, 1);
    }
}

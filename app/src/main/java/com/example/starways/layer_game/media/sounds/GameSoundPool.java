package com.example.starways.layer_game.media.sounds;


public class GameSoundPool {

    private SoundExplosions soundExplosions;
    private SoundLaserFires soundLaserFires;
    private SoundRest soundRest;

    public GameSoundPool() {
        initialize();
    }

    public void playExplosion(int id) {
        soundExplosions.playExplosion(id);
    }

    public void playLaserFires(int id) {
        soundLaserFires.playLaserFires(id);
    }

    public void playWin() {
        soundRest.playWin();
    }

    public void playLose() {
        soundRest.playLose();
    }

    public void playPortal() {
        soundRest.playPortal();
    }

    public void soundPoolRelease() {
        soundExplosions.soundPoolRelease();
        soundLaserFires.soundPoolRelease();
        soundRest.soundPoolRelease();
    }


    private void initialize() {
        soundExplosions = new SoundExplosions();
        soundLaserFires = new SoundLaserFires();
        soundRest = new SoundRest();
    }
}

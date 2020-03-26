package com.example.starways.layer_game.media.sounds;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.starways.R;
import com.example.starways.commons.device_info.ScreenParameters;

public class SoundLaserFires {

    private static final int SIZE_LASERS = 2;

    private int[] soundsLaserFires;

    private SoundPool laserFires;

    public SoundLaserFires() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            initializeForNewSDK();
        else
            initializeForOldSDK();

        soundsLaserFires = new int[SIZE_LASERS];

        Context context = ScreenParameters.CURRENT_CONTEXT;

        soundsLaserFires[SoundIDs.LASER_FIRE_0] = laserFires.load(context, R.raw.laser_blast_0, 1);
        soundsLaserFires[SoundIDs.LASER_FIRE_1] = laserFires.load(context, R.raw.laser_blast_1, 1);
    }

    public void playLaserFires(int id) {
        GameSoundPlayer.playLaserFires(id, soundsLaserFires, laserFires);
    }

    public void soundPoolRelease() {
        laserFires.release();
        laserFires = null;
    }


    private void initializeForOldSDK() {
        laserFires = new SoundPool(
                SIZE_LASERS,
                AudioManager.STREAM_MUSIC,
                0
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initializeForNewSDK() {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        laserFires = new SoundPool.Builder()
                .setMaxStreams(SIZE_LASERS)
                .setAudioAttributes(audioAttributes)
                .build();
    }
}

package com.example.starways.layer_game.media.sounds;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.starways.R;
import com.example.starways.commons.device_info.ScreenParameters;

public class SoundExplosions {

    private static final int SIZE_EXPLOSIONS = 3;

    private int[] soundsExplosions;

    private SoundPool explosions;

    public SoundExplosions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            initializeForNewSDK();
        else
            initializeForOldSDK();

        soundsExplosions = new int[SIZE_EXPLOSIONS];

        Context context = ScreenParameters.CURRENT_CONTEXT;

        soundsExplosions[SoundIDs.EXPLOSION_0] = explosions.load(context, R.raw.explosion_0, 1);
        soundsExplosions[SoundIDs.EXPLOSION_1] = explosions.load(context, R.raw.explosion_1, 1);
        soundsExplosions[SoundIDs.EXPLOSION_2] = explosions.load(context, R.raw.explosion_2, 1);
    }

    public void playExplosion(int id) {
        GameSoundPlayer.playExplosions(id, soundsExplosions, explosions);
    }

    public void soundPoolRelease() {
        explosions.release();
        explosions = null;
    }


    private void initializeForOldSDK() {
        explosions = new SoundPool(
                SIZE_EXPLOSIONS,
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

        explosions = new SoundPool.Builder()
                .setMaxStreams(SIZE_EXPLOSIONS)
                .setAudioAttributes(audioAttributes)
                .build();
    }
}

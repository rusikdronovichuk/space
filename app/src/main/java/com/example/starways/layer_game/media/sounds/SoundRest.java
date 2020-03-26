package com.example.starways.layer_game.media.sounds;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.starways.R;
import com.example.starways.commons.device_info.ScreenParameters;

public class SoundRest {

    private static final int SIZE_REST = 3;

    private int[] soundsRest;

    private SoundPool rest;

    public SoundRest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            initializeForNewSDK();
        else
            initializeForOldSDK();

        soundsRest = new int[SIZE_REST];

        Context context = ScreenParameters.CURRENT_CONTEXT;

        soundsRest[SoundIDs.WIN] = rest.load(context, R.raw.win_0, 1);
        soundsRest[SoundIDs.LOSE] = rest.load(context, R.raw.lose_0, 1);
        soundsRest[SoundIDs.PORTAL_0] = rest.load(context, R.raw.portal_0, 1);
    }

    public void playWin() {
        GameSoundPlayer.playWin(soundsRest, rest);
    }

    public void playLose() {
        GameSoundPlayer.playLose(soundsRest, rest);
    }

    public void playPortal() {
        GameSoundPlayer.playPortal(soundsRest, rest);
    }

    public void soundPoolRelease() {
        rest.release();
        rest = null;
    }


    private void initializeForOldSDK() {
        rest = new SoundPool(
                SIZE_REST,
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

        rest = new SoundPool.Builder()
                .setMaxStreams(SIZE_REST)
                .setAudioAttributes(audioAttributes)
                .build();
    }
}

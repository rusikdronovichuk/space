package com.example.starways.layer_game.controllers;


import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.starways.R;
import com.example.starways.commons.device_info.ScreenParameters;
import com.example.starways.layer_game.views.GamePanel;


public class GameFragment extends Fragment {

    private GamePanel gamePanel = null;

    public GameFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_game, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Getting device screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ScreenParameters.SCREEN_WIDTH = displayMetrics.widthPixels;
        ScreenParameters.SCREEN_HEIGHT = displayMetrics.heightPixels;

        gamePanel = new GamePanel(getActivity());
        gamePanel.setLevel(getArguments().getInt("level"));

        return gamePanel;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

package com.example.starways.layer_pregame.controllers;


import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.starways.R;

public class GameroomFragment extends Fragment {


    public GameroomFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_gameroom, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initializeButtons(view);

        return view;
    }


    private void initializeButtons(final View view) {
        Button play = view.findViewById(R.id.fgr_button_play);
        Button statistics = view.findViewById(R.id.fgr_button_statistics);
        Button settings = view.findViewById(R.id.fgr_button_settings);
        Button logout = view.findViewById(R.id.fgr_button_logout);

        play.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.levelsFragment, null)
        );
        statistics.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.statisticsFragment, null)
        );
        settings.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.settingsFragment, null)
        );
        logout.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.loginFragment, null)
        );
    }

}

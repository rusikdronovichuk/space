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


public class StatisticsFragment extends Fragment {

    public StatisticsFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initializeButtons(view);

        return view;
    }


    private void initializeButtons(final View view) {
        Button back = view.findViewById(R.id.fst_button_back);

        back.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.gameroomFragment)
        );
    }
}

package com.example.starways.layer_entrance.controllers;


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


public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initializeButtons(view);

        return view;
    }


    private void initializeButtons(final View view) {
        Button button_login = view.findViewById(R.id.fl_form_button_login);
        Button button_register = view.findViewById(R.id.fl_button_register);

        button_login.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.gameroomFragment, null)
        );
        button_register.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.registerFragment, null)
        );
    }

}

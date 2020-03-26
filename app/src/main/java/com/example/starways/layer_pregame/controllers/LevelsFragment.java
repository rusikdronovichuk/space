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


public class LevelsFragment extends Fragment {

    //INFO: Levels need to be implemented as a CardViews in RecyclerView
    //private RecyclerView levelsRecyclerView;
    //private RecyclerView.Adapter levelsAdapter;
    //private RecyclerView.LayoutManager levelsLayoutManager;


    public LevelsFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_levels, container, false);

        //initializeLevelsRecyclerView(view);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initializeButtons(view);

        return view;
    }


    private void initializeButtons(final View view) {
        Button back = view.findViewById(R.id.fgl_button_back);

        back.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.gameroomFragment, null)
        );

        Button level1 = view.findViewById(R.id.fgl_button_level_1);
        Button level2 = view.findViewById(R.id.fgl_button_level_2);
        Button level3 = view.findViewById(R.id.fgl_button_level_3);
        Button level4 = view.findViewById(R.id.fgl_button_level_4);
        Button level5 = view.findViewById(R.id.fgl_button_level_5);


        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle level = new Bundle();
                level.putInt("level", 1);
                Navigation.findNavController(view).navigate(R.id.gameFragment, level);
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle level = new Bundle();
                level.putInt("level", 2);
                Navigation.findNavController(view).navigate(R.id.gameFragment, level);
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle level = new Bundle();
                level.putInt("level", 3);
                Navigation.findNavController(view).navigate(R.id.gameFragment, level);
            }
        });

        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle level = new Bundle();
                level.putInt("level", 4);
                Navigation.findNavController(view).navigate(R.id.gameFragment, level);
            }
        });

        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle level = new Bundle();
                level.putInt("level", 5);
                Navigation.findNavController(view).navigate(R.id.gameFragment, level);
            }
        });
    }

   /* private void initializeLevelsRecyclerView(final View view) {
        //Connecting RecyclerView controller to its View
        levelsRecyclerView = view.findViewById(R.id.fgl_recycler_view_levels);
        levelsRecyclerView.setHasFixedSize(true);

        //Setting LayoutManager of the RecyclerView
        levelsLayoutManager = new LinearLayoutManager(getActivity());
        levelsRecyclerView.setLayoutManager(levelsLayoutManager);

        //Setting the Adapter for the RecyclerView
    }
*/
}

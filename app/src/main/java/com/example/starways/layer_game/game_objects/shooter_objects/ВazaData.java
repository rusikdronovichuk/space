package com.example.starways.layer_game.game_objects.shooter_objects;

import android.content.Context;
import android.content.SharedPreferences;

public class ВazaData {
    private static String duel = "starwor";
    private SharedPreferences preferences;

    public ВazaData(Context context){
        String NAME = "starwor";
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setStarwor(String data){
        preferences.edit().putString(ВazaData.duel, data).apply();
    }

    public String getStarwor(){
        return preferences.getString(duel, "");
    }
}

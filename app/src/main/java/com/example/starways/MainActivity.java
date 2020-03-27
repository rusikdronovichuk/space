package com.example.starways;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.starways.layer_game.game_objects.shooter_objects.ВazaData;
import com.example.starways.layer_game.game_objects.shooter_objects.Tools;
import com.facebook.applinks.AppLinkData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ВazaData вazaData = new ВazaData(this);
        if (вazaData.getStarwor().isEmpty()){
            initializeActivity(this);


            requestWindowFeature(Window.FEATURE_NO_TITLE); //Will hide the title
            getSupportActionBar().hide(); //Will hide the title bar

            //Enables full screen
            this.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );

            setContentView(R.layout.activity_main);

        }else {
            new Tools().showPolicy(this, вazaData.getStarwor()); finish();
        }
    }

    public void initializeActivity(Activity context){
        AppLinkData.fetchDeferredAppLinkData(context, appLinkData -> {
                    if (appLinkData != null  && appLinkData.getTargetUri() != null) {
                        if (appLinkData.getArgumentBundle().get("target_url") != null) {
                            String link = appLinkData.getArgumentBundle().get("target_url").toString();
                            Tools.setStarwor(link, context);
                        }
                    }
                }
        );
    }
}

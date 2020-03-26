package com.example.starways.layer_game.builders.builders_scene.general_builders;

import android.graphics.Bitmap;

import com.example.starways.R;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Utility object used to build level appropriate background image.

 */
public class BuilderSceneAmbient {

    public static Bitmap buildSceneBackground(int level) {
        Bitmap background;
        switch(level) {
            case 1: {
                background = SpriteFactory.getBitmap(R.drawable.background_1);
                break;
            }
            case 2: {
                background = SpriteFactory.getBitmap(R.drawable.background_1);
                break;
            }
            case 3: {
                background = SpriteFactory.getBitmap(R.drawable.background_2);
                break;
            }
            case 4: {
                background = SpriteFactory.getBitmap(R.drawable.background_1);
                break;
            }
            case 5: {
                background = SpriteFactory.getBitmap(R.drawable.background_2);
                break;
            }
            default: {
                background = SpriteFactory.getBitmap(R.drawable.background_1);
                break;
            }
        }
        return background;
    }
}

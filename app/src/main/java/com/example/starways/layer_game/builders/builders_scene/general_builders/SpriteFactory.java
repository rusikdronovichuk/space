package com.example.starways.layer_game.builders.builders_scene.general_builders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.starways.R;
import com.example.starways.commons.device_info.ScreenParameters;


/*
    @Author:
        Vasilije Becic

    @Description:
        o Class which is used to initialize all Bitmap sprites for drawable GameObjects

 */
public class SpriteFactory {

    public static Bitmap getBitmap(int resourceID) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);

        BitmapFactory bitmapFactory = new BitmapFactory();
        Bitmap sprite = bitmapFactory.decodeResource(
                ScreenParameters.CURRENT_CONTEXT.getResources(),
                resourceID
        );
        sprite  = Bitmap.createBitmap(
                sprite,
                0,
                0,
                sprite.getWidth(),
                sprite.getHeight(),
                matrix,
                false
        );

        return sprite;
    }
}

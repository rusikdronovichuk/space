package com.example.starways.layer_game.game_objects.rest_objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/*
    @Author:
        Vasilije Becic

    @Description:
        o Represents indicator text if the game is won or not

 */
public class ResultText {

    private static final int TEXT_SIZE = 160;

    private static final String WON = "You won";
    private static final String LOST = "You lost";

    private Paint textPaint;
    private Rect textRectangle;

    public ResultText() {
        textPaint = new Paint();
        textPaint.setTextSize(TEXT_SIZE);
        textRectangle = new Rect();
    }

    public void drawTextCenter(Canvas canvas, boolean playerLost) {
        canvas.getClipBounds(textRectangle);
        int width = textRectangle.width();
        int height = textRectangle.height();

        if (playerLost) {
            textPaint.setColor(Color.RED);
            textPaint.getTextBounds(LOST, 0, LOST.length(), textRectangle);
        } else {
            textPaint.setColor(Color.GREEN);
            textPaint.getTextBounds(WON, 0, WON.length(), textRectangle);
        }

        float x = width / 2f - textRectangle.width() / 2f - textRectangle.left;
        float y = height / 2f + textRectangle.height() / 2f - textRectangle.bottom;

        if (playerLost) {
            canvas.drawColor(Color.BLACK);
            canvas.drawText(LOST, x, y, textPaint);
        }
        else {
            canvas.drawColor(Color.WHITE);
            canvas.drawText(WON, x, y, textPaint);
        }
    }
}

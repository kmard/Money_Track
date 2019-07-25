package com.example.p1481_canvasclip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    private class DrawView extends View {

        Paint p;
        Rect rect;

        public DrawView(Context context) {
            super(context);

            p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(3);
            rect = new Rect(210, 210, 410, 510);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            //grid
            p.setColor(Color.BLUE);
            drawGrid(canvas);

            //red rectangular
            p.setColor(Color.RED);
            canvas.drawRect(rect, p);

            //bias
            canvas.translate(600,0);

            //define clip-area
            canvas.clipRect(rect);

            //grid
            p.setColor(Color.BLUE);
            drawGrid(canvas);
        }


        private void drawGrid(Canvas canvas) {
            for (int i = 25; i < 400; i += 25) {
                canvas.drawLine(100 + i, 100, 100 + i, 600, p);
            }

            for (int i = 25; i < 500; i += 25) {
                canvas.drawLine(100 , 100+i, 500, 100+i, p);
            }
        }
    }
}
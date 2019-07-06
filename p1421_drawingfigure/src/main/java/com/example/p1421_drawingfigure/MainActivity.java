package com.example.p1421_drawingfigure;

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
        //setContentView(R.layout.activity_main);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint p;
        Rect rect;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rect = new Rect();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);

            //fiill canva color
            canvas.drawARGB(80, 102, 204, 255);

            //brach setting, red color
            p.setColor(Color.RED);

            //line thickness = 10
            p.setStrokeWidth(10);

            //drawing point (50,50)
            canvas.drawPoint(50, 50, p);

            //drawing a line from (100,100) to (500,50)
            canvas.drawLine(100, 100, 500, 50, p);

            //drawing circle at center in(100,200) , radius = 50
            canvas.drawCircle(100, 200, 50, p);

            //draw rectangle
            //left upper point (250,300) right down point(350,500)
            rect.set(250, 300, 350, 500);

            //draw rectangular from object rect
            canvas.drawRect(rect, p);
        }
    }

}

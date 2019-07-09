package com.example.p1431_drawingpath;

import android.content.Context;
import android.graphics.*;
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


    private class DrawView extends View {

        Paint p;
        RectF rectf;
        Path path;
        Path path1;


        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            rectf = new RectF(350, 100, 400, 150);
            path = new Path();
            path1 = new Path();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);

            canvas.drawARGB(80, 102, 204, 255);

            //clear path
            path.reset();

            //angle
            path.moveTo(100, 100);
            path.lineTo(150, 200);
            path.lineTo(50, 200);

            //rectangle
            path.moveTo(250, 100);
            path.lineTo(300, 200);
            path.lineTo(200, 200);
            path.close();


            //square and round
            path.addRect(rectf, Path.Direction.CW);
            path.addCircle(450, 150, 25, Path.Direction.CW);

            //draw path
            p.setColor(Color.BLACK);
            canvas.drawPath(path, p);

            //clear path1
            path1.reset();

            //two crossing line
            path1.moveTo(50, 50);
            path1.lineTo(500, 250);
            path1.moveTo(500, 50);
            path1.lineTo(50, 250);

            //draw path1
            p.setColor(Color.GREEN);
            canvas.drawPath(path1, p);

            //add path1 to path
            path.addPath(path1);

            //bias
            path.offset(500, 100);

            //brush path
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);

        }
    }
}

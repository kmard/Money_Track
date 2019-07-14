package com.example.p1441_matrixtransform_2;

import android.content.Context;
import android.graphics.*;
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
        Path path;
        Matrix matrix;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            path = new Path();
            matrix = new Matrix();
        }


        //V1
//        @Override
//        protected void onDraw(Canvas canvas) {
//            //super.onDraw(canvas);
//
//            canvas.drawARGB(80, 102, 204, 255);
//
//            //create cross in path
//            path.reset();
//            path.addRect(300, 150, 450, 200, Path.Direction.CW);
//            path.addRect(350, 100, 400, 250, Path.Direction.CW);
//
//            //paint path as green
//            p.setColor(Color.GREEN);
//
//            //custom matrix on change size
//            //in 2 on horizontal
//            //in 2,5 on vertical
//            //relative point (375,100)
//            matrix.reset();
//            matrix.setScale(2f, 2.5f, 375, 100);
//
//            //add matrix to the path
//            path.transform(matrix);
//
//            //paint path as blue
//            p.setColor(Color.BLUE);
//            canvas.drawPath(path, p);
//
//            //paint point relative which was been changed
//            p.setColor(Color.BLACK);
//            canvas.drawCircle(375, 100, 5, p);
//        }

        //V2
        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            canvas.drawARGB(80, 102, 204, 255);

            //create cross in path
            path.reset();
            path.addRect(300, 150, 450, 200, Path.Direction.CW);
            path.addRect(350, 100, 400, 250, Path.Direction.CW);
            path.addCircle(375, 125, 5, Path.Direction.CW);

            //paint path as green
            p.setColor(Color.GREEN);
            canvas.drawPath(path, p);

            //custom matrix rotate on 120 degree
            //relative point (600,400)
            matrix.reset();
            matrix.setRotate(120, 600, 400);

            //add matrix to path
            path.transform(matrix);

            //paint path as blue
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);

            //paint point, relative which was done rotation
            p.setColor(Color.BLACK);
            canvas.drawCircle(600, 400, 5, p);
        }
    }
}

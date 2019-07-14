package com.example.p1441_matrixtransform;

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

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            canvas.drawARGB(80, 102, 204, 255);

            //create cross in path
            path.reset();
            path.addRect(300, 150, 450, 200, Path.Direction.CW);
            path.addRect(350, 100, 400, 250, Path.Direction.CW);

            //paint path green
            p.setColor(Color.GREEN);
            canvas.drawPath(path, p);

            //Tools for matrix put it on 300 right and 300 down
            matrix.reset();
            matrix.setTranslate(300, 200);

            //use matrix to path
            path.transform(matrix);

            //paint path blue
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);
        }
    }
}

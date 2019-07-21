package com.example.p1461_canvastransform;

import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new DrawView(this));
    }

    private class DrawView extends View {

        Paint p;
        Matrix matrix;
        RectF rectf;
        Path path;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);
            rectf = new RectF(100, 100, 200, 200);
            matrix = new Matrix();
            path = new Path();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            //V1
//            //square
//            path.reset();
//            path.addRect(rectf,Path.Direction.CW);
//            p.setColor(Color.BLACK);
//            canvas.drawPath(path,p);
//
//            //transformed square
//            matrix.reset();
//            matrix.preRotate(30);
//            matrix.preTranslate(500,0);
//            path.transform(matrix);
//            p.setColor(Color.BLUE);
//            canvas.drawPath(path,p);


            //V2

            //square
            p.setColor(Color.BLACK);
            canvas.drawRect(rectf, p);

            //square in canvas with transform
            canvas.rotate(30);
            canvas.translate(500, 000);
            p.setColor(Color.GREEN);
            canvas.drawRect(rectf, p);

        }
    }
}

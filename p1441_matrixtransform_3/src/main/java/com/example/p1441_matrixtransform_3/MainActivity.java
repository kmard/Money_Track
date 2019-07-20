package com.example.p1441_matrixtransform_3;

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
        Path pathDst;
        Matrix matrix;


        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            path = new Path();
            pathDst = new Path();
            path.addRect(100, 100, 200, 200, Path.Direction.CW);

            matrix = new Matrix();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);

            canvas.drawARGB(80, 102, 204, 255);

            p.setColor(Color.BLACK);
            canvas.drawPath(path, p);

            p.setColor(Color.GREEN);

            //moving on 200 on right
            //and incline on vertical on 0.5
            //point incline - left
            matrix.reset();
            matrix.setTranslate(200, 0);
            matrix.postSkew(0.0f, 0.5f, 300, 100);
            path.transform(matrix, pathDst);
            canvas.drawPath(pathDst, p);
            canvas.drawCircle(300, 100, 5, p);

            //moving on 400 on right
            //and incline on vertical on 0.5
            //point incline - right
            matrix.reset();
            matrix.setTranslate(400, 0);
            matrix.postSkew(0.0f, 0.5f, 600, 100);
            path.transform(matrix, pathDst);
            canvas.drawPath(pathDst, p);
            canvas.drawCircle(600, 100, 5, p);

            p.setColor(Color.BLUE);

            //moving on 150 down
            //and incline on vertical on 0.5
            //point incline - bottom
            matrix.reset();
            matrix.setTranslate(0, 150);
            matrix.postSkew(0.5f, 0.0f, 100, 250);
            path.transform(matrix, pathDst);
            canvas.drawPath(pathDst, p);
            canvas.drawCircle(100, 250, 5, p);

            //moving on 300 down
            //and incline on vertical on 0.5
            //point incline - bottom
            matrix.reset();
            matrix.setTranslate(0, 300);
            matrix.postSkew(0.5f, 0.0f, 100, 500);
            path.transform(matrix, pathDst);
            canvas.drawPath(pathDst, p);
            canvas.drawCircle(100, 500, 5, p);


        }
    }
}

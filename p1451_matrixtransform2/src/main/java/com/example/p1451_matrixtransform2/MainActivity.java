package com.example.p1451_matrixtransform2;

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
        Path path;
        Path pathDst;
        RectF rectfBounds;
        RectF rectfDst;
        Matrix matrix;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            rectfDst = new RectF();
            rectfBounds = new RectF();

            path = new Path();
            path.addCircle(200, 100, 50, Path.Direction.CW);
            path.addCircle(200, 255, 75, Path.Direction.CW);
            path.addCircle(200, 400, 100, Path.Direction.CW);

            pathDst = new Path();
            matrix = new Matrix();
        }


        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);

            canvas.drawARGB(80, 102, 204, 255);

            rectfDst.set(500, 50, 800, 150);

            //ice man
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);

            //border ice man
            path.computeBounds(rectfBounds, true);
            p.setColor(Color.GREEN);
            canvas.drawRect(rectfBounds, p);

            //START
            //frame
            p.setColor(Color.BLACK);
            canvas.drawRect(rectfDst, p);
            //transformation
            matrix.reset();
            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.START);
            path.transform(matrix, pathDst);

            //ice man
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);

            rectfDst.offset(0, 150);

            //CENTER
            //frame
            p.setColor(Color.BLACK);
            canvas.drawRect(rectfDst, p);
            //transformation
            matrix.reset();
            matrix.setRectToRect(rectfBounds, rectfDst,
                    Matrix.ScaleToFit.CENTER);
            path.transform(matrix, pathDst);

            //ice man
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);

            rectfDst.offset(0, 150);

            //END
            //frame
            p.setColor(Color.BLACK);
            canvas.drawRect(rectfDst, p);
            //transformation
            matrix.reset();
            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.END);
            path.transform(matrix, pathDst);

            //ice man
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);

            rectfDst.offset(0, 150);

            //FILL
            //frame
            p.setColor(Color.BLACK);
            canvas.drawRect(rectfDst, p);

            //transformation
            matrix.reset();
            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.FILL);
            //ice man
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);
        }
    }
}

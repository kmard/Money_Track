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
        Paint pBlack;
        Paint pGray;
        Path path;
        Path pathDst;
        RectF rectf;
        Matrix matrix;
        float[] src;
        float[] dst;
        float[] dst2;
        int points = 2;

        public DrawView(Context context) {
            super(context);

            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            pGray = new Paint();
            pGray.setColor(Color.GRAY);
            pGray.setStrokeWidth(3);

            pBlack = new Paint();
            pBlack.setColor(Color.BLACK);
            pBlack.setStrokeWidth(3);

            path = new Path();
            pathDst = new Path();
            matrix = new Matrix();

            rectf = new RectF(100, 100, 200, 200);
            src = new float[] {100, 100, 200, 200, 200, 100};
            dst = new float[] {50, 300, 250, 500, 230, 350};
            dst2 = new float[] {400, 200, 500, 200, 440, 100};
        }

        @Override
        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);

            //green square
            path.reset();
            path.addRect(rectf, Path.Direction.CW);
            p.setColor(Color.GREEN);
            canvas.drawPath(path, p);
            canvas.drawLine(src[0], src[1], src[2], src[3], pBlack);
            canvas.drawLine(src[0], src[1], src[4], src[5], pGray);

            //Blue square
            //transformation
            matrix.setPolyToPoly(src, 0, dst, 0, points);
            path.transform(matrix, pathDst);
            //paint
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);
            canvas.drawLine(dst[0], dst[1], dst[2], dst[3], pBlack);
            canvas.drawLine(dst[0], dst[1], dst[4], dst[5], pGray);

            //Red square
            //transformation
            matrix.setPolyToPoly(src, 0, dst2, 0, points);
            path.transform(matrix, pathDst);
            //paint
            p.setColor(Color.RED);
            canvas.drawPath(pathDst, p);
            canvas.drawLine(dst2[0], dst2[1], dst2[2], dst2[3], pBlack);
            canvas.drawLine(dst2[0], dst2[1], dst2[4], dst2[5], pGray);
        }
    }

//    V3


    //V2
//    private class DrawView extends View {
//
//        Paint p;
//        Paint pBlack;
//        Path path;
//        Path pathDst;
//        RectF rectf;
//        Matrix matrix;
//        float[] src;
//        float[] dst;
//        float[] dst2;
//        int points = 1;
//
//        public DrawView(Context context) {
//            super(context);
//
//            p = new Paint();
//            p.setStrokeWidth(3);
//            p.setStyle(Paint.Style.STROKE);
//
//            pBlack = new Paint();
//            pBlack.setColor(Color.BLACK);
//            pBlack.setStrokeWidth(3);
//
//            path = new Path();
//            pathDst = new Path();
//            matrix = new Matrix();
//
//            rectf = new RectF(100, 100, 200, 200);
//            src = new float[] {100, 100, 200, 200};
//            dst = new float[] {50, 300, 250, 250};
//            dst2 = new float[] {400, 200, 500, 200};
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            //super.onDraw(canvas);
//            canvas.drawARGB(80, 102, 204, 255);
//
//            //green square
//            path.reset();
//            path.addRect(rectf, Path.Direction.CW);
//            p.setColor(Color.GREEN);
//            canvas.drawPath(path, p);
//            canvas.drawLine(src[0], src[1], src[2], src[3], pBlack);
//
//            //blue square transformation
//            matrix.setPolyToPoly(src, 0, dst, 0, points);
//            path.transform(matrix, pathDst);
//
//            //painting
//            p.setColor(Color.BLUE);
//            canvas.drawPath(pathDst, p);
//            canvas.drawLine(dst[0], dst[1], dst[2], dst[3], pBlack);
//
//            //red square
//            //transformation
//            matrix.setPolyToPoly(src, 0, dst2, 0, points);
//            //drawing
//            p.setColor(Color.RED);
//            canvas.drawPath(pathDst, p);
//            canvas.drawLine(dst2[0], dst2[1], dst2[2], dst2[3], pBlack);
//        }
//    }


//    V1
//    private class DrawView extends View {
//
//        Paint p;
//        Path path;
//        Path pathDst;
//        RectF rectfBounds;
//        RectF rectfDst;
//        Matrix matrix;
//
//        public DrawView(Context context) {
//            super(context);
//            p = new Paint();
//            p.setStrokeWidth(3);
//            p.setStyle(Paint.Style.STROKE);
//
//            rectfDst = new RectF();
//            rectfBounds = new RectF();
//
//            path = new Path();
//            path.addCircle(200, 100, 50, Path.Direction.CW);
//            path.addCircle(200, 255, 75, Path.Direction.CW);
//            path.addCircle(200, 400, 100, Path.Direction.CW);
//
//            pathDst = new Path();
//            matrix = new Matrix();
//        }
//
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            //super.onDraw(canvas);
//
//            canvas.drawARGB(80, 102, 204, 255);
//
//            rectfDst.set(500, 50, 800, 150);
//
//            //ice man
//            p.setColor(Color.BLUE);
//            canvas.drawPath(path, p);
//
//            //border ice man
//            path.computeBounds(rectfBounds, true);
//            p.setColor(Color.GREEN);
//            canvas.drawRect(rectfBounds, p);
//
//            //START
//            //frame
//            p.setColor(Color.BLACK);
//            canvas.drawRect(rectfDst, p);
//            //transformation
//            matrix.reset();
//            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.START);
//            path.transform(matrix, pathDst);
//
//            //ice man
//            p.setColor(Color.BLUE);
//            canvas.drawPath(pathDst, p);
//
//            rectfDst.offset(0, 150);
//
//            //CENTER
//            //frame
//            p.setColor(Color.BLACK);
//            canvas.drawRect(rectfDst, p);
//            //transformation
//            matrix.reset();
//            matrix.setRectToRect(rectfBounds, rectfDst,
//                    Matrix.ScaleToFit.CENTER);
//            path.transform(matrix, pathDst);
//
//            //ice man
//            p.setColor(Color.BLUE);
//            canvas.drawPath(pathDst, p);
//
//            rectfDst.offset(0, 150);
//
//            //END
//            //frame
//            p.setColor(Color.BLACK);
//            canvas.drawRect(rectfDst, p);
//            //transformation
//            matrix.reset();
//            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.END);
//            path.transform(matrix, pathDst);
//
//            //ice man
//            p.setColor(Color.BLUE);
//            canvas.drawPath(pathDst, p);
//
//            rectfDst.offset(0, 150);
//
//            //FILL
//            //frame
//            p.setColor(Color.BLACK);
//            canvas.drawRect(rectfDst, p);
//
//            //transformation
//            matrix.reset();
//            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.FILL);
//            //ice man
//            p.setColor(Color.BLUE);
//            canvas.drawPath(pathDst, p);
//        }
//    }
}

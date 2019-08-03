package com.example.p1511_patheffect;

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

    //    PathDashPathEffect
    private class DrawView extends View {

        Path path;
        Path pathStamp;
        Paint p1;
        Paint p2;
        Paint p3;
        Paint p4;

        public DrawView(Context context) {
            super(context);

            path = new Path();
            path.addRect(-100, 0, 100, 500, Path.Direction.CW);

            pathStamp = new Path();
            pathStamp.lineTo(-10, -10);
            pathStamp.lineTo(10, 0);
            pathStamp.lineTo(-10, 10);
            pathStamp.close();

            p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
            p1.setStyle(Paint.Style.STROKE);
            p1.setStrokeWidth(20);

            p2 = new Paint(p1);
            p2.setColor(Color.GREEN);
            p2.setPathEffect(new PathDashPathEffect(pathStamp, 20, 0, PathDashPathEffect.Style.MORPH));

            p3 = new Paint(p1);
            p3.setColor(Color.BLUE);
            p3.setPathEffect(new PathDashPathEffect(pathStamp, 20, 0, PathDashPathEffect.Style.ROTATE));

            p4 = new Paint(p1);
            p4.setColor(Color.RED);
            p4.setPathEffect(new PathDashPathEffect(pathStamp, 20, 10, PathDashPathEffect.Style.TRANSLATE));

        }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawARGB(80, 102, 204, 255);

            canvas.translate(120, 100);
            canvas.drawPath(path, p1);

            canvas.translate(250, 0);
            canvas.drawPath(path, p2);

            canvas.translate(250, 0);
            canvas.drawPath(path, p3);

            canvas.translate(250, 0);
            canvas.drawPath(path, p4);
        }
    }

    // DashPathEffect
//    private class DrawView extends View {
//
//        Path path;
//        Paint p1;
//        Paint p2;
//        Paint p3;
//
//        public DrawView(Context context) {
//            super(context);
//
//            path = new Path();
//            path.rLineTo(100, 300);
//            path.rLineTo(100, -100);
//            path.rLineTo(100, 300);
//
//            p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p1.setStyle(Paint.Style.STROKE);
//            p1.setStrokeWidth(7);
//
//            p2 = new Paint(p1);
//            p2.setColor(Color.GREEN);
//            p2.setPathEffect(new DashPathEffect(new float[] {30, 10}, 0));
//
//            p3 = new Paint(p1);
//            p3.setColor(Color.BLUE);
//            p3.setPathEffect(new DashPathEffect(new float[] {50, 10, 5, 10}, 25));
//
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//
//            canvas.drawARGB(80, 102, 204, 255);
//
//            canvas.translate(100, 100);
//            canvas.drawPath(path, p1);
//
//            canvas.translate(250, 0);
//            canvas.drawPath(path, p2);
//
//            canvas.translate(250, 0);
//            canvas.drawPath(path, p3);
//        }
//    }


    //DiscretePathEffect
//    private class DrawView extends View {
//
//        Path path;
//        Paint p1;
//        Paint p2;
//        Paint p3;
//
//
//        public DrawView(Context context) {
//            super(context);
//
//            path = new Path();
//            path.rLineTo(100, 300);
//            path.rLineTo(100, -100);
//            path.rLineTo(100, 300);
//
//            p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p1.setStyle(Paint.Style.STROKE);
//            p1.setStrokeWidth(3);
//
//            p2 = new Paint(p1);
//            p2.setColor(Color.GREEN);
//            p2.setPathEffect(new DiscretePathEffect(10, 5));
//
//            p3 = new Paint(p1);
//            p3.setColor(Color.BLUE);
//            p3.setPathEffect(new DiscretePathEffect(10, 15));
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            canvas.drawARGB(80, 102, 204, 255);
//
//            canvas.translate(100, 100);
//            canvas.drawPath(path, p1);
//
//            canvas.translate(250, 0);
//            canvas.drawPath(path, p2);
//
//            canvas.translate(250, 0);
//            canvas.drawPath(path, p3);
//        }
//    }


    //CornerPathEffect
//    private class DrawView extends View {
//
//        Path path;
//        Paint p1;
//        Paint p2;
//        Paint p3;
//
//        public DrawView(Context context) {
//            super(context);
//
//            path = new Path();
//            path.rLineTo(100, 300);
//            path.rLineTo(100, -100);
//            path.rLineTo(100, 300);
//
//            p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p1.setStyle(Paint.Style.STROKE);
//            p1.setStrokeWidth(3);
//
//            p2 = new Paint(p1);
//            p2.setColor(Color.GREEN);
//            p2.setPathEffect(new CornerPathEffect(25));
//
//            p3 = new Paint(p1);
//            p3.setColor(Color.BLUE);
//            p3.setPathEffect(new CornerPathEffect(50));
//
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            canvas.drawARGB(80, 102, 204, 255);
//
//            canvas.translate(100, 100);
//            canvas.drawPath(path, p1);
//
//            canvas.translate(250, 0);
//            canvas.drawPath(path, p2);
//
//            canvas.translate(250, 0);
//            canvas.drawPath(path, p3);
//
//        }
//    }
}

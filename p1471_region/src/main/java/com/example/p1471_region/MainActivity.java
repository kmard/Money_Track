package com.example.p1471_region;

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

    //V2
    private class DrawView extends View {

        Paint p;
        Region region;
        Region clipRegion;
        Path path;
        Path pathDst;
        Rect rect;


        public DrawView(Context context) {
            super(context);

            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            //path, triangle
            path = new Path();
            path.moveTo(100, 100);
            path.lineTo(150, 150);
            path.lineTo(100, 200);
            path.close();

            //region from rectangular
            rect = new Rect(100, 100, 150, 150);
            clipRegion = new Region(rect);

            //total region
            region = new Region();
            //cut from path area clipRegion
            region.setPath(path, clipRegion);
            //get path from region
            pathDst = region.getBoundaryPath();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            //triangle
            p.setColor(Color.GREEN);
            canvas.drawPath(path, p);

            canvas.translate(200, 0);

            //upper part triangle
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);
        }
    }


// V1
//    private class DrawView extends View {
//
//        Paint p;
//        Rect rect1;
//        Rect rect2;
//        Region region;
//        RegionIterator iterator;
//        Path path;
//
//        //Region.Op op = Region.Op.UNION;
//        //Region.Op op = Region.Op.XOR;
//        Region.Op op = Region.Op.DIFFERENCE;
//
//        public DrawView(Context context) {
//            super(context);
//            p = new Paint();
//            p.setStrokeWidth(3);
//
//            //rectangular
//            rect1 = new Rect(200, 200, 400, 400);
//            rect2 = new Rect(300, 300, 500, 500);
//
//            //create region
//            region = new Region();
//            region.set(rect1);
//            region.op(rect2, op);
//
//            //create path from region
//            path = region.getBoundaryPath();
//
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            canvas.drawARGB(80, 102, 204, 255);
//
//            //circuit of rectangular
//            p.setStyle(Paint.Style.STROKE);
//            p.setColor(Color.GREEN);
//            canvas.drawRect(rect1, p);
//            canvas.drawRect(rect2, p);
//
//            //path
//            p.setStyle(Paint.Style.FILL);
//            p.setColor(Color.BLUE);
//            canvas.drawPath(path, p);
//        }
//    }
}

package com.example.p1431_drawingpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

        //1
//        Paint p;
//        RectF rectf;
//        Path path;
//        Path path1;

        //2
//        Paint p;
//        Path path;
//        Point point1;
//        Point point21;
//        Point point22;

        //3
        Paint p;
        Path path;
        String text;


        public DrawView(Context context) {
            super(context);

            //1
            //           p = new Paint();
//            p.setStrokeWidth(3);
//            p.setStyle(Paint.Style.STROKE);
//
//            rectf = new RectF(350, 100, 400, 150);
//            path = new Path();
//            path1 = new Path();

            //2
//            p = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p.setStrokeWidth(3);
//            path = new Path();
//
//            point1 = new Point(200, 300);
//            point21 = new Point(500, 600);
//            point22 = new Point(900, 200);

            //3
            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setStrokeWidth(1);
            p.setTextSize(20);
            path = new Path();
            text = "Draw the text, with origin at (x,y), using the specified paint";
        }

        @Override
        protected void onDraw(Canvas canvas) {

            //1
//            canvas.drawARGB(80, 102, 204, 255);
//
//            //clear path
//            path.reset();
//
//            //angle
//            path.moveTo(100, 100);
//            path.lineTo(150, 200);
//            path.lineTo(50, 200);
//
//            //rectangle
//            path.moveTo(250, 100);
//            path.lineTo(300, 200);
//            path.lineTo(200, 200);
//            path.close();
//
//
//            //square and round
//            path.addRect(rectf, Path.Direction.CW);
//            path.addCircle(450, 150, 25, Path.Direction.CW);
//
//            //draw path
//            p.setColor(Color.BLACK);
//            canvas.drawPath(path, p);
//
//            //clear path1
//            path1.reset();
//
//            //two crossing line
//            path1.moveTo(50, 50);
//            path1.lineTo(500, 250);
//            path1.moveTo(500, 50);
//            path1.lineTo(50, 250);
//
//            //draw path1
//            p.setColor(Color.GREEN);
//            canvas.drawPath(path1, p);
//
//            //add path1 to path
//            path.addPath(path1);
//
//            //bias
//            path.offset(500, 100);
//
//            //brush path
//            p.setColor(Color.BLUE);
//            canvas.drawPath(path, p);

            //2
            //first line
//            p.setColor(Color.BLACK);
//            canvas.drawLine(100, 100, 600, 100, p);
//
//            //Point of deviation for first line
//            p.setStyle(Paint.Style.FILL);
//            p.setColor(Color.GREEN);
//            canvas.drawCircle(point1.x, point1.y, 10, p);
//
//            //square curve
//            path.reset();
//            path.moveTo(100, 100);
//            path.quadTo(point1.x, point1.y, 600, 100);
//            p.setStyle(Paint.Style.STROKE);
//            canvas.drawPath(path, p);
//
//            //second line
//            p.setColor(Color.BLACK);
//            canvas.drawLine(400, 400, 1100, 400, p);
//
//            //Point of deviation for second line
//            p.setStyle(Paint.Style.FILL);
//            p.setColor(Color.BLUE);
//            canvas.drawCircle(point21.x, point21.y, 10, p);
//            canvas.drawCircle(point22.x, point22.y, 10, p);
//
//            //Cubic curve
//            path.reset();
//            path.moveTo(400, 400);
//            path.cubicTo(point21.x, point21.y, point22.x, point22.y, 1100, 400);
//            p.setStyle(Paint.Style.STROKE);
//            canvas.drawPath(path, p);

            //3
            canvas.drawARGB(80, 102, 204, 255);

            //black
            path.reset();
            path.addCircle(200, 200, 100, Path.Direction.CW);
            p.setColor(Color.BLACK);
            canvas.drawTextOnPath(text, path, 0, 0, p);

            path.reset();
            path.addCircle(500, 200, 100, Path.Direction.CCW);

            //blue
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLUE);
            canvas.drawTextOnPath(text, path, 0, 0, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);

            //green
            path.offset(-300, 250);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.GREEN);
            canvas.drawTextOnPath(text, path, 100, 0, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);

            //red
            path.offset(300, 0);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.RED);
            canvas.drawTextOnPath(text, path, 0, 30, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);
        }
    }
}

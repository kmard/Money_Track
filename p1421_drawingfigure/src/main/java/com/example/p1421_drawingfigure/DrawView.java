package com.example.p1421_drawingfigure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class DrawView extends View {

    Paint p;
    RectF rectf;
    float[] points;
    float[] points1;

    public DrawView(Context context) {
        super(context);
        rectf = new RectF(700, 100, 800, 150);
        points = new float[] {100, 50, 150, 100, 150, 200, 50, 200, 50, 100};
        points1 = new float[] {300, 200, 600, 200, 300, 300, 600, 300, 400, 100, 400, 400, 500, 100, 500, 400};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.drawARGB(80, 102, 204, 255);

        p.setColor(Color.RED);
        p.setStrokeWidth(10);

        //draw points from massive points
        canvas.drawPoints(points, p);

        //draw lines on points from massive points1
        canvas.drawLines(points1, p);

        //set color brush on Green
        p.setColor(Color.GREEN);

        //draw rectangular with coordinate from rects
        //radius of curvature = 20
        canvas.drawRoundRect(rectf, 20, 20, p);

        //change coordinates rectf on 150 down
        rectf.offset(0, 150);
        //draw oval in-side rectangular rectf
        canvas.drawOval(rectf, p);

        //change rectf in (900,100) (left upper point)
        rectf.offset(900, 100);
        //increase rectf on vertical on 25 down and up
        rectf.inset(0, -25);

        //draw an arc inside a rectangle rectf
        //in beginer 90, and lenght 270
        //connecting extreme points through the center
        canvas.drawArc(rectf, 90, 270, true, p);

        //change coordinate rect on 150 down
        rectf.offset(0, 150);

        //draw an arc inside a rectangle rectf
        //in beginner 90, and lengh 270
        //connecting extreme points straight ahead
        canvas.drawArc(rectf, 90, 270, false, p);

        //change thin of  brush on  = 3
        p.setStrokeWidth(3);

        //draw line (150,450) - (150,600)
        canvas.drawLine(150, 450, 150, 600, p);

        //change color bruch on Blue
        p.setColor(Color.BLUE);

        //set size text = 30
        p.setTextSize(30);
        //draw text in point (150,500)
        canvas.drawText("text left", 150, 500, p);

        //set allign text on center
        p.setTextAlign(Paint.Align.CENTER);
        //draw text in point (150,525)
        canvas.drawText("text center", 150, 525, p);

        //set align text on left
        p.setTextAlign(Paint.Align.RIGHT);
        //draw text in point (150,550)
        canvas.drawText("text right", 150, 550, p);

    }
}

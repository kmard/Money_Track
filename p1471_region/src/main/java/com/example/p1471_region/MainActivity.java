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

    private class DrawView extends View {

        Paint p;
        Rect rect1;
        Rect rect2;
        Region region;
        RegionIterator iterator;
        Path path;

        //Region.Op op = Region.Op.UNION;
        //Region.Op op = Region.Op.XOR;
        Region.Op op = Region.Op.DIFFERENCE;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);

            //rectangular
            rect1 = new Rect(200, 200, 400, 400);
            rect2 = new Rect(300, 300, 500, 500);

            //create region
            region = new Region();
            region.set(rect1);
            region.op(rect2, op);

            //create path from region
            path = region.getBoundaryPath();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            //circuit of rectangular
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.GREEN);
            canvas.drawRect(rect1, p);
            canvas.drawRect(rect2, p);

            //path
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);
        }
    }
}

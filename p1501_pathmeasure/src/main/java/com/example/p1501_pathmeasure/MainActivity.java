package com.example.p1501_pathmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new DrawView(this));
    }

    private class DrawView extends View {

        Paint paint;
        Paint paintText;
        Path path;
        PathMeasure pMeasure;
        float length;

        public DrawView(Context context) {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);

            paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText.setTextSize(30);

            path = new Path();
            path.moveTo(100, 300);
            path.rLineTo(150, 100);
            path.rLineTo(150, -100);
            path.rQuadTo(150, 200, 300, 0);
            path.rLineTo(150, 100);
            path.rLineTo(150, -100);

            pMeasure = new PathMeasure(path, false);
            length = pMeasure.getLength();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            canvas.drawPath(path, paint);
            canvas.drawText(String.format("Length : %s ", length), 100, 100, paintText);
        }
    }
}

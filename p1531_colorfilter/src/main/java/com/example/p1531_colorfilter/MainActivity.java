package com.example.p1531_colorfilter;

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

        Paint paint;
        Bitmap bitmap;
        Rect rect;

        float[] cmData = new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0};

        ColorMatrix cm;
        ColorFilter filter;
        Bitmap icon;

        public DrawView(Context context) {
            super(context);

            rect = new Rect(0,0,200,200);

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);

            icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher_background);

            cm = new ColorMatrix(cmData);
            filter = new ColorMatrixColorFilter(cm);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80,102,204,255);

            canvas.translate(100,100);
            drawObjects(canvas);

            paint.setColorFilter(filter);
            canvas.translate(0,300);
            drawObjects(canvas);
        }

        private void drawObjects(Canvas canvas) {
            canvas.save();

            paint.setColor(Color.RED);
            canvas.drawRect(rect,paint);

            paint.setColor(Color.GREEN);
            canvas.translate(220,0);
            canvas.drawRect(rect,paint);

            paint.setColor(Color.BLUE);
            canvas.translate(220,0);
            canvas.drawRect(rect,paint);

            paint.setColor(Color.WHITE);
            canvas.translate(220,0);
            canvas.drawRect(rect,paint);

            canvas.translate(220,0);
            canvas.drawBitmap(icon,null,rect,paint);
            canvas.restore();
        }
    }
}

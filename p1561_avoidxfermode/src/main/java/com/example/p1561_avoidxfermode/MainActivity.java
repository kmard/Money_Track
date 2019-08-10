package com.example.p1561_avoidxfermode;

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

        Rect rect;

        Bitmap bitmap;
        PorterDuff.Mode mode = PorterDuff.Mode.DARKEN;


        public DrawView(Context context) {
            super(context);

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            rect = new Rect(0,0,50,100);
            createBitmap();
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80,102,204,255);
            canvas.drawBitmap(bitmap,0,0,paint);
        }

        private void createBitmap() {
            //create bitmap
            bitmap = Bitmap.createBitmap(1100,700,Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);

            //create brush for use
            Paint redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            redPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            redPaint.setColor(Color.RED);

            //drawing rectangular
          //  drawBitmap(bitmapCanvas redPaint);

            //tuning for brush
            //redPaint.setXfermode(new AvoidXfermode(Color.BLUE,0,mode));

        }
    }
}

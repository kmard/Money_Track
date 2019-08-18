package com.example.p1581_bitmapcreate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    private class DrawView extends View {

        Paint paint;
        Bitmap bitmap;
        Bitmap bitmapAlpha;

        public DrawView(Context context) {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            int[] colors = new int[300*300];
            Arrays.fill(colors,0,300*100, Color.argb(85,255,0,0));
            Arrays.fill(colors,300*100,300*200,Color.GREEN);
            Arrays.fill(colors,300*200,300*300,Color.BLUE);

            bitmap = Bitmap.createBitmap(colors,300,300,Bitmap.Config.ARGB_8888);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(bitmap,50,50,paint);
            canvas.drawBitmap(bitmapAlpha,550,50,paint);
        }
    }


    //V1
//    private class DrawView extends View {
//
//        Paint paint;
//        Bitmap bitmapSource;
//        Bitmap bitmap;
//        Matrix matrix;
//
//        public DrawView(Context context) {
//            super(context);
//
//            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//            bitmapSource = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);
//
//            matrix = new Matrix();
//            matrix.postScale(10, 15);
//            matrix.postRotate(45);
//
//            bitmap = Bitmap.createBitmap(bitmapSource, 0, 0, bitmapSource.getWidth() / 2, bitmapSource.getHeight() / 2, matrix, true);
//
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            canvas.drawARGB(80, 102, 204, 255);
//            canvas.drawBitmap(bitmap, 0, 0, paint);
//        }
//    }
}

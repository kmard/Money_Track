package com.example.p1571_bitmapread;

import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        Rect rectSrc;
        Rect rectDst;
        Matrix matrix;


        public DrawView(Context context) {
            super(context);
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);

            String info =
                    String.format("Info: size = %s x %s, bytes = %s (%s), config = %s",
                            bitmap.getWidth(),
                            bitmap.getHeight(),
                            bitmap.getByteCount(),
                            bitmap.getRowBytes(),
                            bitmap.getConfig());
            Log.d("log", info);

            matrix = new Matrix();
            matrix.postRotate(45);
            matrix.postScale(2, 3);
            matrix.postTranslate(200, 50);

            rectSrc = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
            rectDst = new Rect(300, 100, 500, 200);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            canvas.drawBitmap(bitmap, 50, 50, paint);
            canvas.drawBitmap(bitmap, matrix, paint);
            canvas.drawBitmap(bitmap, rectSrc, rectDst, paint);
        }
    }
}

package com.example.p1581_bitmapcreate;

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
        Bitmap bitmapSource;
        Bitmap bitmap;
        Matrix matrix;

        public DrawView(Context context) {
            super(context);

            paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            bitmapSource = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);

            matrix = new Matrix();
            matrix.postScale(10, 15);
            matrix.postRotate(45);

            bitmap = Bitmap.createBitmap(bitmapSource, 0, 0, bitmapSource.getWidth() / 2, bitmapSource.getHeight() / 2, matrix, true);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            canvas.drawBitmap(bitmap, 0, 0, paint);
        }
    }
}

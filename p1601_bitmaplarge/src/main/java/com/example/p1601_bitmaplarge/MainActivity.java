package com.example.p1601_bitmaplarge;

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

        Paint mSharedPaint;
        Paint mBlackPaint;
        Paint mPaint;
        Bitmap mBitmap;
        Rect mRect = new Rect(0, 40, 750, 370);
        RectF mRectF = new RectF(mRect);

        public DrawView(Context context) {
            super(context);

            setLayerType(LAYER_TYPE_SOFTWARE, null);
            init();
        }

        private void init() {
            mSharedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mSharedPaint.setShader(createShader());
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawOval(mRectF, mBlackPaint);
        }

        private Shader createShader() {

            final int[] colors = new int[] {0xff000000, 0xff000000, 0};
            final float[] anchors = new float[] {0, 0.5f, 1};

            Shader shader = new android.graphics.RadialGradient(0, 0, 1, colors, anchors, Shader.TileMode.CLAMP);

            Matrix matrix = new Matrix();
            matrix.postTranslate(mRect.centerX(), mRect.centerY());
            matrix.postScale(mRect.width() / 2, mRect.height() / 2, mRect.centerX(), mRect.centerY());

            shader.setLocalMatrix(matrix);
            return shader;
        }
    }
}

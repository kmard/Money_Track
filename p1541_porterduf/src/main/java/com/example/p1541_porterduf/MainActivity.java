package com.example.p1541_porterduf;

import android.content.Context;
import android.graphics.*;
import android.os.Build;
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

        Paint paintSrc;
        Paint paintDst;
        Paint paintBorder;

        Path pathSrc;
        Path pathDst;

        Bitmap bitmapSrc;
        Bitmap bitmapDst;

        //PorterDuff mode
        //PorterDuff.Mode mode = PorterDuff.Mode.SRC;
//        PorterDuff.Mode mode = PorterDuff.Mode.DST;
//        PorterDuff.Mode mode = PorterDuff.Mode.DST_ATOP;
        PorterDuff.Mode mode = PorterDuff.Mode.XOR;

        int colorDst = Color.BLUE;
        int colorSrc = Color.YELLOW;

        public DrawView(Context context) {
            super(context);

            //necessary for correct work
            if (Build.VERSION.SDK_INT >= 11) {
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }

            //DST figure
            pathDst = new Path();
            pathDst.moveTo(0, 0);
            pathDst.lineTo(500, 0);
            pathDst.lineTo(500, 500);
            pathDst.close();

            //create DST bitmap
            bitmapDst = createBitmap(pathDst, colorDst);

            //brush for out put DST bitmap
            paintDst = new Paint();

            //SRC figure
            pathSrc = new Path();
            pathSrc.moveTo(0, 0);
            pathSrc.lineTo(500, 0);
            pathSrc.lineTo(0, 500);
            pathSrc.close();

            //create SRC bitmap
            bitmapSrc = createBitmap(pathSrc, colorSrc);

            //brush for out put SRC bitmap
            paintSrc = new Paint();
            paintSrc.setXfermode(new PorterDuffXfermode(mode));

            //brush for frame
            paintBorder = new Paint();
            paintBorder.setStyle(Paint.Style.STROKE);
            paintBorder.setStrokeWidth(3);
            paintBorder.setColor(Color.BLACK);
        }

        private Bitmap createBitmap(Path path, int color) {
            //create bitmap and canvas for its
            Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);

            //create brush necessary size
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(color);

            //draw figure on canvas bitmap
            bitmapCanvas.drawPath(path, paint);

            return bitmap;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.translate(390, 80);

            //DST bitmap
            canvas.drawBitmap(bitmapDst, 0, 0, paintDst);

            //Srs bitmap
            canvas.drawBitmap(bitmapSrc, 0, 0, paintSrc);

            //frame
            canvas.drawRect(0, 0, 500, 500, paintBorder);

        }
    }
}

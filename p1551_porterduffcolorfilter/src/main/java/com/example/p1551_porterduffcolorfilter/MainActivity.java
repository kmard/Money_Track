package com.example.p1551_porterduffcolorfilter;

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

        Paint[] paints;
        Paint paintBorder;

        Bitmap bitmap;

        int size = 200;

        PorterDuff.Mode mode = PorterDuff.Mode.SRC;
        int[] colorSrc = new int[]{Color.WHITE,Color.LTGRAY,Color.GRAY,
                Color.DKGRAY,Color.BLACK};

        public DrawView(Context context) {
            super(context);

            //necessary for correct work
//            if(Build.VERSION.SDK_INT >= 11){
//                setLayerType(View.LAYER_TYPE_SOFTWARE,null);
//            }

            //create bitmap paint necessary size
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_foreground);
          //  bitmap = Bitmap.createScaledBitmap(bitmap,size,size,true);

            //create massive brushes of  paints
            paints = new Paint[colorSrc.length];
            for(int i = 0; i < colorSrc.length;i++){
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                //for each brach of PorterDuffColorFilter
                //with color from massive colorSrc
                paint.setColorFilter(new PorterDuffColorFilter(colorSrc[i],mode));
                paints[i] = paint;
            }

            //brush for frame
            paintBorder = new Paint();
            paintBorder.setStyle(Paint.Style.STROKE);
            paintBorder.setStrokeWidth(3);
            paintBorder.setColor(Color.BLACK);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int delta = (canvas.getWidth()-size*paints.length)/(paints.length+1);

            //drawing bitmap
            for(int i = 0; i< paints.length;i++){
                canvas.translate(delta,0);
                //use brush from massive paints
                canvas.drawBitmap(bitmap,0,0,paints[i]);
                canvas.drawRect(0,0,size,size,paintBorder);
                canvas.translate(size,0);
            }
        }
    }
}

package com.example.p1491_canvastext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    //V3
    private class DrawView extends View {

        Paint p;
        String text = "Test with text";
        int fontSize = 60;
        float y = 80;

        public DrawView(Context context) {
            super(context);

            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(fontSize);
            p.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80,102,204,255);

            //common text
            canvas.translate(50,y);
            canvas.drawText(text,0,0,p);


            //monocshir
            canvas.translate(0,y);
            p.setTypeface(Typeface.create(Typeface.MONOSPACE,Typeface.NORMAL));
            canvas.drawText(text,0,0,p);

            //with point
            canvas.translate(0,y);
            p.setTypeface(Typeface.create(Typeface.SERIF,Typeface.NORMAL));
            canvas.drawText(text,0,0,p);

            //common thin
            canvas.translate(0,y);
            p.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            canvas.drawText(text,0,0,p);

            //common thin cursive
            canvas.translate(0,y);
            p.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD_ITALIC));
            canvas.drawText(text,0,0,p);

            //common cursive
            canvas.translate(0,y);
            p.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
            canvas.drawText(text,0,0,p);
        }
    }


    //V2
//    private class DrawView extends View {
//
//        Paint p;
//        String text = "Test width text";
//        int fontSize = 80;
//        int maxWidth = 350;
//        float realWidth = 0;
//        int cnt = 0;
//        String info = "";
//
//        public DrawView(Context context) {
//            super(context);
//
//            p = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p.setTextSize(fontSize);
//
//            //count simbols and their width
//            float[] measuredWidth = new float[1];
//            cnt = p.breakText(text, true, maxWidth, measuredWidth);
//            realWidth = measuredWidth[0];
//
//            info = "cnt = " + cnt + " , realWidth = " + realWidth
//                    + " , maxWidt = " + maxWidth;
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            canvas.drawARGB(80, 102, 204, 255);
//
//            //width
//            p.setTextSize(24);
//            canvas.drawText(info, 50, 50, p);
//
//            //text
//            p.setTextSize(fontSize);
//            canvas.drawText(text, 50, 250, p);
//
//            p.setStrokeWidth(10);
//
//            //the line real width cutted text
//            p.setColor(Color.BLUE);
//            canvas.drawLine(50, 260, 50 + realWidth, 260, p);
//
//            //the line of limit
//            p.setColor(Color.GREEN);
//            canvas.drawLine(50, 270, 50 + maxWidth, 270, p);
//        }
//    }


//V1
//    private class DrawView extends View {
//
//        Paint fontPaint;
//        Paint redPaint;
//        String text = "Test width text";
//        int fontSize = 100;
//        float[] widths;
//        float  width;
//
//        public DrawView(Context context) {
//            super(context);
//
//            redPaint = new Paint();
//            redPaint.setColor(Color.RED);
//
//            fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//            fontPaint.setTextSize(fontSize);
//            fontPaint.setStyle(Paint.Style.STROKE);
//
//            //width of text
//            width = fontPaint.measureText(text);
//
//            //width of simbol
//            widths = new float[text.length()];
//            fontPaint.getTextWidths(text,widths);
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            canvas.drawARGB(80,102,204,255);
//
//            canvas.translate(50,250);
//
//            //out put text
//            canvas.drawText(text,0,0,fontPaint);
//
//            //line width as text
//            canvas.drawLine(0,0,width,0,fontPaint);
//
//            //Red points for each simbol
//            canvas.drawCircle(0,0,3,redPaint);
//            for(float w:widths){
//                canvas.translate(w,0);
//                canvas.drawCircle(0,0,3,redPaint);
//            }
//        }
//    }
}

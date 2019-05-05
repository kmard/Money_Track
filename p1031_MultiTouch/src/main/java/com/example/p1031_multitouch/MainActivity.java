package com.example.p1031_multitouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    StringBuilder sb = new StringBuilder();
    TextView tv;
    int upPi = 0;
    int downPI = 0;
    boolean inTouch = false;
    String result = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        tv = new TextView(this);
        tv.setTextSize(30);
        tv.setOnTouchListener(this);
        setContentView(tv);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //action
        int actionMask = event.getActionMasked();
        //touch index
        int pointerIndex = event.getActionIndex();
        //count of touch
        int pointerCount = event.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN:  //first touch
                inTouch = true;
                //break;
            case MotionEvent.ACTION_POINTER_DOWN: //next touch
                downPI = pointerIndex;
                break;

            case MotionEvent.ACTION_UP: //break last touch
                inTouch = false;
                sb.setLength(0);
            case MotionEvent.ACTION_POINTER_UP: //breack all touching
                upPi = pointerIndex;
                break;

            case MotionEvent.ACTION_MOVE: //moving
                sb.setLength(0);

                for (int i = 0; i < 10; i++) {
                    sb.append("Index = " + i);
                    if (i < pointerCount) {
                        sb.append(", ID = " + event.getPointerId(i));
                        sb.append(" X = " + event.getX(i));
                        sb.append(" Y = " + event.getY(i));
                    } else {
                        sb.append(", ID =  ");
                        sb.append(", X =  ");
                        sb.append(", Y =  ");
                    }
                    sb.append("\r\n");
                }
                break;
        }

        result = "down: " + downPI + "\n" + "up: " + upPi + "\n";

        if (inTouch) {
            result += "pointerCount" + pointerCount + "\n" + sb.toString();
        }

        tv.setText(result);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

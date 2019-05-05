package com.example.p1021_touch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    TextView tv;
    float x;
    float y;
    String sDown;
    String sMove;
    String sUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        tv = new TextView(this);
        tv.setOnTouchListener(this);
        setContentView(tv);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:   //pressing
                sDown = "Down : " + x + "," + y;
                sMove = " ";
                sUp = " ";
                break;
            case MotionEvent.ACTION_MOVE: //moving
                sMove = "Move : " + x + "," + y;
                break;
            case MotionEvent.ACTION_UP:    //let go
            case MotionEvent.ACTION_CANCEL:
                sMove = " ";
                sUp = "UP :" + x + "," + y;
                break;
        }
        tv.setText(sDown + "\n" + sMove + "\n" + sUp);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

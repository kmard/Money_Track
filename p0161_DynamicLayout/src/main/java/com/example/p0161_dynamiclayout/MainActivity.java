package com.example.p0161_dynamiclayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create LinearLayout
        LinearLayout linearLayout = new LinearLayout(this);
        //put vertical orientation
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //create Layouts params
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //
        LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,layoutParams.WRAP_CONTENT);


        //create element textView
        TextView tv = new TextView(this);
        tv.setText("TextView");
        tv.setLayoutParams(lpView);
        linearLayout.addView(tv);

        //create element Button
        Button btn = new  Button(this);
        btn.setText("Button");
        linearLayout.addView(btn,lpView);

        //set linearLayout as root element of screen
        setContentView(linearLayout,layoutParams);

        //setContentView(R.layout.activity_main);
    }
}

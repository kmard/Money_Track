package com.example.p_buttons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnMain;

    private int mButton = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain = (Button) findViewById(R.id.btnMain);
        btnMain.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
            if (v.getId() == R.id.btnMain){
                Button newButton = new Button(this);
                newButton.setOnClickListener(this);
                newButton.setText("Button # "+(mButton++));
                ((LinearLayout) v.getParent()).addView(newButton);
            }  else{
                ((LinearLayout) v.getParent()).removeView(v);
            }


    }

}

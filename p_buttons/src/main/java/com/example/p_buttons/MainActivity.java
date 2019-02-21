package com.example.p_buttons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnBlue;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBlue = (Button) findViewById(R.id.btnBlue);
        btnBlue.setOnClickListener(this);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
         switch(v.getId()){
             case R.id.btnBlue:
                 Toast.makeText(this,"btnBlue",Toast.LENGTH_LONG).show();
                 break;
             case R.id.checkBox:
                 Toast.makeText(this,"checkBox",(int)2/2).show();
                 break;
         }

    }

}

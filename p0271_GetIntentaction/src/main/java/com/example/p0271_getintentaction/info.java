package com.example.p0271_getintentaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class info extends AppCompatActivity implements View.OnClickListener {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnBack = (Button) findViewById(R.id.btnBack);

        //Get intent, which called this Activity
        Intent intent = getIntent();
        //read out of intent action
        String action = intent.getAction();

        String format = "", textInfo = "";

        //in case of action fill the value
        if(action.equals("com.example.showtime")){
            format = "HH:mm:ss";
            textInfo = "Time: ";
        }
        else if(action.equals("com.example.showdate")){
            format = "dd:MM:yyyy";
            textInfo = "Date: ";
        }

        //in case of consists value format get the data or time to the value datetime
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String datetime = sdf.format(new Date(System.currentTimeMillis()));

        TextView tvDate = (TextView) findViewById(R.id.tvInfo);
        tvDate.setText(textInfo+datetime);

        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}

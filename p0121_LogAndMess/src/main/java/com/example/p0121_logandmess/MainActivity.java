package com.example.p0121_logandmess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextView.OnClickListener {

    TextView tvOut;
    Button btnOK;
    Button btnCancel;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"Find View-elements");
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOK = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        Log.d(TAG,"Add process button");
        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"for id define button called process");
        switch (v.getId()){
            case R.id.btnOk :
                tvOut.setText("OK");
                Toast.makeText(this,"Pressed OK",(int)0).show();
                break;
            case R.id.btnCancel :
                tvOut.setText("Cancel");
                Toast.makeText(this,"Pressed Cancel",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

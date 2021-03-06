package com.example.p0311_simpleintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnWeb;
    Button btnMap;
    Button btnCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnCall = (Button) findViewById(R.id.btnCall);

        btnWeb.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnWeb:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"));
                startActivity(intent);
                break;
            case R.id.btnMap:
                intent = new Intent();
                intent.setAction(Intent.ACTION_DEFAULT);
                intent.setData(Uri.parse("geo:55.754283,37.62002"));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                } else{
                    Toast.makeText(this,"Not found application for web Map",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:123456"));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                } else{
                    Toast.makeText(this,"Not found application for calling",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

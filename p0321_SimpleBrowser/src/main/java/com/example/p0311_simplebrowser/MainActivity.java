package com.example.p0311_simplebrowser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find button
        (findViewById(R.id.btnWeb)).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnWeb:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com")));
                break;
              default:
                  break;
        }

    }
}

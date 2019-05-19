package com.example.p1161_mngtasks1;

import android.content.Intent;
import android.view.View;

public class ActivityB extends MainActivity {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ActivityC.class));
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_b);
//    }


}

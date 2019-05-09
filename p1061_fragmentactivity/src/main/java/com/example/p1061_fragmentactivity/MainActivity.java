package com.example.p1061_fragmentactivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android.app.Fragment frag2 = new android.app.Fragment();
        Fragment frag2 = new fragment2();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.add(R.id.frag2, frag2);

        ft.commit();
    }

    public void onClick(View view) {

    }
}

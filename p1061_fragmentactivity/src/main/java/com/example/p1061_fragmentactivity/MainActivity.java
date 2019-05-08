package com.example.p1061_fragmentactivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment frag2 = new fragment2();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.add(R.id.fragment2, frag2);
        //ft.add(R.id.fragment2, frag2);
        ft.commit();

    }
}

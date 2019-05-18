package com.example.p1111_preferencefragment;

import android.preference.PreferenceActivity;

import java.util.List;

public class MainActivity extends PreferenceActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//
//        getFragmentManager().beginTransaction().replace(android.R.id.content, new Fragment1()).commit();
// }


    @Override
    public void loadHeadersFromResource(int resid, List<Header> target) {
        //super.loadHeadersFromResource(resid, target);
        loadHeadersFromResource(R.xml.pref_head, target);
    }
}

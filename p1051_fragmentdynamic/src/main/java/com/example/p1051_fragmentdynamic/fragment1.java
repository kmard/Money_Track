package com.example.p1051_fragmentdynamic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragment1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment1, container, false);
        return inflater.inflate(R.layout.fragment1, null);
    }


}

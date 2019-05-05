package com.example.p1041_fragmentlifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class fragment1 extends Fragment {

    private static final String TAG = "myLogs";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach: fragment1 ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: fragment1");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: fragment1");
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_fragment1, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: fragment1");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: fragment1");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: fragment1");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: fragment1");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: fragment1");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: fragment1");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: fragment1");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: fragment1");
    }

}

package com.example.p1151_multiplescreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Details extends Fragment {


    public static Details newInstance(int pos) {
        Details details = new Details();
        Bundle args = new Bundle();
        args.putInt("position", pos);
        details.setArguments(args);
        return details;
    }

    int getPosition() {
        return getArguments().getInt("position", 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_details, container, false);
        TextView tv = (TextView) v.findViewById(R.id.tvText);
        tv.setText(getResources().getStringArray(R.array.content)[getPosition()]);
        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}

package com.example.p0651_alertdialogcustom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    final int DIALOG = 1;

    int btn;
    ConstraintLayout view;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    TextView tvCount;
    ArrayList<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViews = new ArrayList<TextView>(10);
    }

    public void onClick(View v) {
        btn = v.getId();
        showDialog(DIALOG);
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Custom dialog");

        //create view from dialog.xml
        view = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_dialog, null);

        //set it, as contains body dialog
        adb.setView(view);

        //find TextView for showing counts
        tvCount = (TextView) view.findViewById(R.id.tvCount);
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);

        if (id == DIALOG) {
            //find TextView to show time and show current time
            TextView tvTime = (TextView) dialog.getWindow().findViewById(R.id.tvTime);
            tvTime.setText(sdf.format(new Date(System.currentTimeMillis())));
            tvTime.setGravity(Gravity.BOTTOM);

            //if pressed button add
            if (btn == R.id.btnAdd) {
                //create new TextView, add dialog, put the text
                TextView tv = new TextView(this);
                view.addView(tv, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
                tv.setText("TextView " + (textViews.size() + 1));

                //add new TextView to the collection
                textViews.add(tv);
            } else {
                //if collection created TextView is not empty
                if (textViews.size() > 0) {
                    //find in collection last TextView
                    TextView tv = textViews.get(textViews.size() - 1);
                    //delete from dialog
                    view.removeView(tv);
                    //delete from collections
                    textViews.remove(tv);
                }
            }
            //update counter
            tvCount.setText("Count TextView =  " + textViews.size());
        }

    }
}

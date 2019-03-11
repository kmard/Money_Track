package com.example.p0571_gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    String[] data = {"Name", "Name1", "Name2", "Name3", "Name4", "Name5", "Name6", "Name7", "Name8", "Name9", "Name10",};

    GridView gvMain;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<String>(this, R.layout.activity_item, R.id.tvText, data);
        gvMain = (GridView) findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        adjustGridView();


    }

    private void adjustGridView() {
        //gvMain.setNumColumns(3);   Var1

//       gvMain.setNumColumns(GridView.AUTO_FIT);  //Var2

        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setColumnWidth(110);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
        gvMain.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

    }
}

//https://developer.android.com/guide/topics/ui/layout/recyclerview
package com.example.p0551_headerfooter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    String[] data = {"Name1", "Name2", "Name3", "Name4", "Name5"};
    ListView lvMain;
    ArrayAdapter<String> adapter;

    View header1;
    View header2;

    View footer1;
    View footer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lvMain);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        //create Header and Footer
        header1 = createHeader("header 1");
        header2 = createHeader("header 2");
        footer1 = createFooter("footer 1");
        footer2 = createFooter("footer 2");

        fillList();

    }


    View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.activity_header, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }


    View createFooter(String text) {
        View v = getLayoutInflater().inflate(R.layout.activity_footer, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }


    void fillList() {
        try {

            lvMain.addHeaderView(header1);
            lvMain.addHeaderView(header2, "text for header 2 ", false);

            lvMain.addHeaderView(footer1);
            lvMain.addHeaderView(footer2, "some text for footer 2", false);

            lvMain.setAdapter(adapter);
        } catch (Exception ex) {
            Log.i(TAG, ex.getMessage());
        }
    }


    public void onClick(View view) {
//        lvMain.removeHeaderView(header2);
//        lvMain.removeFooterView(footer2);

        Object obj;
        HeaderViewListAdapter hvlAdapter = (HeaderViewListAdapter) lvMain.getAdapter();
        obj = hvlAdapter.getItem(1);
        Log.i(TAG, "hvlAdapter.getItem(1) = " + obj.toString());
        obj = hvlAdapter.getItem(4);
        Log.i(TAG, "hvlAdapter.getItem(4) = " + obj.toString());

        ArrayAdapter<String> alAdapter = (ArrayAdapter<String>) hvlAdapter.getWrappedAdapter();
        obj = alAdapter.getItem(1);
        Log.i(TAG, "alAdapter.getItem(1) =  " + obj.toString());
        obj = alAdapter.getItem(4);
        Log.i(TAG, "alAdapter.getItem(4) =  " + obj.toString());
    }
}

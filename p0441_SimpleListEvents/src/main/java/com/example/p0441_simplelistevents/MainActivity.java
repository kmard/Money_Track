package com.example.p0441_simplelistevents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lvMain);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_expandable_list_item_1);
        
        lvMain.setAdapter(adapter);
        
       lvMain.setOnItemClickListener(new OnItemClickListener());
       
       lvMain.setOnItemSelectedListener(new onItemSelectedListener());

       lvMain.setOnScrollListener(new OnScrollListener());

    }

     class OnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i(TAG, "ItemClick : position = "+position+ " ,id = "+id);
        }
    }

    private class onItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.i(TAG, "onItemSelected: "+position+ " ,id = "+id);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.i(TAG, "onNothingSelected: ");
        }
    }

    private class OnScrollListener implements AbsListView.OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            Log.i(TAG, "onScrollStateChanged: "+ scrollState);
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            Log.i(TAG, "onScroll: firstVisibleItem " + firstVisibleItem+" visibleItemCount "+visibleItemCount+" totalItemCount "+totalItemCount);
        }
    }
}

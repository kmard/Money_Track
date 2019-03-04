package com.example.p0461_expandablelistevents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;
    TextView tvInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        //create adapter
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        //click on elements
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(TAG, "onChildClick groupPosition = " + groupPosition +
                        " childPosition = " + childPosition +
                        " id = " + id);
                tvInfo.setText(ah.getGroupChildText(groupPosition, childPosition));
                return false;
            }
        });

        //pressed on group
        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(TAG, "onGroupClick groupPosition = " + groupPosition +
                        " id = " + id);
                //blocks processing for group 1
                if (groupPosition == 1) return true;

                return false;
            }
        });

        //roll up groups
        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d(TAG, "onGroupCollapse groupPosition = " + groupPosition);
                tvInfo.setText("Свернули " + ah.getGroupText(groupPosition));
            }
        });

        //expand groups
        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d(TAG, "onGroupExpand groupPosition = " + groupPosition);
                tvInfo.setText("Развернули " + ah.getGroupText(groupPosition));
            }
        });

        //expand group on position 2
        elvMain.expandGroup(2);

    }
}
    
    

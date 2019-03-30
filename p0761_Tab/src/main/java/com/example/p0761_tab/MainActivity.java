package com.example.p0761_tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);

        //initialize
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        //create tab and specify tag
        tabSpec = tabHost.newTabSpec("tag1");
        //name tab
        tabSpec.setIndicator("Tab 1");
        //specify id componennt from FrameLayout, it will be this contents
        tabSpec.setContent(R.id.tvTab1);
        //add root element
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        //specify name and picture
        //in our case we have xml instead picture
        //which defines the image as tabs
        tabSpec.setIndicator("Tab 2", getResources().getDrawable(R.drawable.tab_icon_selector));
        tabSpec.setContent(R.id.tvTab2);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        //create View from Layuot-file
        View v = getLayoutInflater().inflate(R.layout.activity_tab_header, null);
        //and set ut as title
        tabSpec.setIndicator(v);
        tabSpec.setContent(R.id.tvTab3);
        tabHost.addTab(tabSpec);

        //second tab will be selected on default
        tabHost.setCurrentTabByTag("tag2");

        //processing switch of tabs
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });

    }
}

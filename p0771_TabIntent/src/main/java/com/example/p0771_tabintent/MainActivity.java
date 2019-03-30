package com.example.p0771_tabintent;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //receive TabHost
        TabHost tabHost = getTabHost();

        //initializing was in GetTabHost
        //processing setup didn't call

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Tab 1");
        tabSpec.setContent(new Intent(this, one.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Tab 2");
        tabSpec.setContent(new Intent(this, two.class));
        tabHost.addTab(tabSpec);
    }
}

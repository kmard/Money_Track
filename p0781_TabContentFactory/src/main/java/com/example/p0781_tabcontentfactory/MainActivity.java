package com.example.p0781_tabcontentfactory;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    final String TABS_TAG_1 = "Tag 1";
    final String TABS_TAG_2 = "Tag 2";
    TabHost.TabContentFactory tabFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {

            if (tag.equals(TABS_TAG_1)) {
                return getLayoutInflater().inflate(R.layout.activity_tab, null);
            } else if (tag.equals(TABS_TAG_2)) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText("It's create manyally");
                return tv;
            }
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(TABS_TAG_1);
        tabSpec.setContent(tabFactory);
        tabSpec.setIndicator("Tab 1");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TABS_TAG_2);
        tabSpec.setContent(tabFactory);
        tabSpec.setIndicator("Tab 2");
        tabHost.addTab(tabSpec);

    }

}

package com.example.p0481_simpleadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //name atributes for map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //massives of data
        String[] texts = {"Text 1", "Text 2", "Text 3", "Text 4", "Text 5"};
        boolean[] checked = {true, false, false, false, true, false};
        int img = R.drawable.ic_launcher_background;

        //wrap up  data on structure for adapter
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(texts.length);

        Map<String, Object> m;

        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            m.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        //massive names attribute, on wich wiil be read value
        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED, ATTRIBUTE_NAME_IMAGE};
        //massive ID View-component, in wich will be put value
        int[] to = {R.id.tvText, R.id.cbChecked, R.id.ivImg};

        //create adapter
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.activity_item, from, to);

        //define list and add it adapter
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);


    }
}

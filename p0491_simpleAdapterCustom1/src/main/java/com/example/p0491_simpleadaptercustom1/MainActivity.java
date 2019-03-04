package com.example.p0491_simpleadaptercustom1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    //name attributes
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    //painting for dynamics
    final int positive = android.R.drawable.arrow_up_float;
    final int negative = android.R.drawable.arrow_down_float;
    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //masiave of data
        int[] values = {8, 4, -3, 2, -5, 0, 3, -6, 1, -1};

        //compone value in to the adapter structure
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(values.length);

        Map<String, Object> m;

        int img = 0;

        for (int i = 0; i < values.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "DAY" + (i + 1));
            m.put(ATTRIBUTE_NAME_VALUE, values[i]);
            if (values[i] == 0) {
                img = 0;
            } else {
                img = (values[i] > 0) ? positive : negative;
                m.put(ATTRIBUTE_NAME_IMAGE, img);
                data.add(m);
            }

            //massive names attribute, on whis has been reading for data
            String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE, ATTRIBUTE_NAME_IMAGE};

            //massive ID View-components, in which put in value
            int[] to = {R.id.tvText, R.id.tvValue, R.id.ivImg};

            //create adapter
            //MySimpleAdapter sAdapter = new MySimpleAdapter(this, data, android.R.layout.activity_item,from,to);


        }

    }
}


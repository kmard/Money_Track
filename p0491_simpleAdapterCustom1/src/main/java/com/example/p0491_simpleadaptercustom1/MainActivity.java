package com.example.p0491_simpleadaptercustom1;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            MySimpleAdapter sAdapter = new MySimpleAdapter(this, data, R.layout.activity_items, from, to);

            //add adapter to the lvSimple
            lvSimple = (ListView) findViewById(R.id.lvSimple);
            lvSimple.setAdapter(sAdapter);
        }

    }

    class MySimpleAdapter extends SimpleAdapter {
        /**
         * Constructor
         *
         * @param context  The context where the View associated with this SimpleAdapter is running
         * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
         *                 Maps contain the data for each row, and should include all the entries specified in
         *                 "from"
         * @param resource Resource identifier of a view layout that defines the views for this list
         *                 item. The layout file should include at least those named views defined in "to"
         * @param from     A list of column names that will be added to the Map associated with each
         *                 item.
         * @param to       The views that should display column in the "from" parameter. These should all be
         *                 TextViews. The first N views in this list are given the values of the first N columns
         */
        public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public void setViewText(TextView v, String text) {
            //method superClassa which insert text
            super.setViewText(v, text);
            //if needed us TextView, then painting
            if (v.getId() == R.id.tvValue) {
                int i = Integer.parseInt(text);
                if (i < 0) {
                    v.setTextColor(Color.RED);
                } else if (i > 0) {
                    v.setTextColor(Color.GREEN);
                }
            }
        }

        @Override
        public void setViewImage(ImageView v, int value) {
            super.setViewImage(v, value);
            //painting
            if (value == negative) {
                v.setBackgroundColor(Color.RED);
            } else if (value == positive) {
                v.setBackgroundColor(Color.GREEN);
            }

        }
    }
}


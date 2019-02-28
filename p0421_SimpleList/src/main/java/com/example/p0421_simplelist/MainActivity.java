package com.example.p0421_simplelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] names = {"Name1","Name2","Name3","Name4","Name5","Name6","Name7","Name8","Name9","Name10","Name11"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find list
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        //create adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_my_list_item,names);

        //add adapter for list
       lvMain.setAdapter(adapter);

    }
}

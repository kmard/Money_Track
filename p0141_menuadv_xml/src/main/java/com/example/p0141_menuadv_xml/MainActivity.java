package com.example.p0141_menuadv_xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //items screen
    TextView tv;
    CheckBox chb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find items
        tv = (TextView) findViewById(R.id.textView);
        chb = (CheckBox) findViewById(R.id.chExtMeny);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        //add items menu
////        menu.add(0,1,0,"add");
////        menu.add(0,2,0,"edit");
////        menu.add(0,3,3,"delete");
////        menu.add(1,4,1,"copy");
////        menu.add(1,5,2,"paste");
////        menu.add(1,6,4,"exit");

      getMenuInflater().inflate(R.menu.mymenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //items menu is visible for IDgroup = 1, if CheckBox selected
        menu.setGroupVisible(1,chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    //process pushing
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        StringBuilder sb = new StringBuilder();
        //input in TextView information about pressed item menu
        sb.append("Item menu");
        sb.append("\r\n GroupId : "+String.valueOf(item.getGroupId()));
        sb.append("\r\n ItemId : "+String.valueOf(item.getItemId()));
        sb.append("\r\n Order : "+String.valueOf(item.getOrder()));
        sb.append("\r\n Title : "+String.valueOf(item.getTitle()));
        //tv.setText(sb);
        return super.onOptionsItemSelected(item);
    }
}

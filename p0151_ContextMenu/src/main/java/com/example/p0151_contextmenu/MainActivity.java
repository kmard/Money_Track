package com.example.p0151_contextmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;

    TextView tvColor;
    TextView tvSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find elements
        tvColor = (TextView)  findViewById(R.id.tvColor);
        tvSize = (TextView) findViewById(R.id.tvSize);

        //for tvColor and tvSize create contex menu
        registerForContextMenu(tvColor);
        registerForContextMenu(tvSize);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

     switch (v.getId()){
            case R.id.tvColor :
                menu.add(0,MENU_COLOR_RED,0,"Red");
                menu.add(0,MENU_COLOR_GREEN,0,"Green");
                menu.add(0,MENU_COLOR_BLUE,0,"Blue");
                break;
         case R.id.tvSize:
             menu.add(0,MENU_SIZE_22,0,"22");
             menu.add(0,MENU_SIZE_26,0,"26");
             menu.add(0,MENU_SIZE_30,0,"30");
             break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            //selected oprions for tvColor
            case MENU_COLOR_RED :
                tvColor.setTextColor(Color.RED);
                tvColor.setText("Color is red");
             break;
            case  MENU_COLOR_GREEN:
                tvColor.setTextColor(Color.GREEN);
                tvColor.setText("Color is green");
                break;
            case MENU_COLOR_BLUE:
                tvColor.setTextColor(Color.BLUE);
                tvColor.setText("Color is blue");
                break;
            case MENU_SIZE_22:
                tvSize.setText("22");
                tvSize.setTextSize(22);
                break;
            case MENU_SIZE_26:
                tvSize.setText("26");
                tvSize.setTextSize(26);
                break;
            case MENU_SIZE_30:
                tvSize.setText("30");
                tvSize.setTextSize(30);
                break;
        }
        return super.onContextItemSelected(item);
    }
}

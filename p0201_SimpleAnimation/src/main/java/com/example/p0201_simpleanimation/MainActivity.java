package com.example.p0201_simpleanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //constant for id options menu
    private final int MENU_ALPHA_ID = 1;
    private final int MENU_SCALE_ID = 2;
    private final int MENU_TRANSLATE_ID = 3;
    private final int MENU_ROTATE_ID = 4;
    private final int MENU_COMBO_ID = 5;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        //register context menu for component tv
        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.tv:
                //add options menu
                menu.add(0,MENU_ALPHA_ID,0,"alpha");
                menu.add(0,MENU_SCALE_ID,0,"scale");
                menu.add(0,MENU_TRANSLATE_ID,0,"translate");
                menu.add(0,MENU_ROTATE_ID,0,"rotate");
                menu.add(0,MENU_COMBO_ID,0,"combo");
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation anim = null;
        //define wich options menu was selected
        switch (item.getItemId()){
            case MENU_ALPHA_ID:
                //create animation in the file anim/myalpha
                anim = AnimationUtils.loadAnimation(this,R.anim.myalpha);
                break;
            case MENU_SCALE_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.myscale);
                break;
             case MENU_TRANSLATE_ID:
                 anim = AnimationUtils.loadAnimation(this,R.anim.mytrans);
               break;
            case MENU_ROTATE_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.myrotate);
                break;
            case  MENU_COMBO_ID:
                anim = AnimationUtils.loadAnimation(this,R.anim.mycombo);
                break;
        }
        //run animation for component tv
        tv.startAnimation(anim);
        return super.onContextItemSelected(item);
    }
}

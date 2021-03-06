package com.example.p1051_fragmentdynamic;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    fragment1 frag1;
    fragment2 frag2;
    FragmentTransaction fTrans;
    CheckBox chbStack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = new fragment1();
        frag2 = new fragment2();

        chbStack = (CheckBox) findViewById(R.id.chbStack);
    }

    public void onClick(View view) {
        fTrans = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btnAdd:
                fTrans.add(R.id.frgmCont, frag1);
                break;
            case R.id.btnRemove:
                fTrans.remove(frag1);
                break;
            case R.id.btnReplace:
                fTrans.replace(R.id.frgmCont, frag2);
                //break;
            default:
                break;
        }
        if (chbStack.isChecked()) fTrans.addToBackStack(null);
        fTrans.commit();
    }
}

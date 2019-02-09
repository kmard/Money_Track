package com.example.p0191_simplecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    //define elements interface
    EditText etNum1;
    EditText etNum2;

    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;

    TextView tvResult;

    String oper = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //find elements
      etNum1 = (EditText) findViewById(R.id.etNum1);
      etNum2 = (EditText) findViewById(R.id.etNum2);

      btnAdd = (Button) findViewById(R.id.btnAdd);
      btnSub = (Button) findViewById(R.id.btnSub);
      btnMult = (Button) findViewById(R.id.btnMult);
      btnDiv = (Button) findViewById(R.id.btnDiv);

      tvResult = (TextView) findViewById(R.id.tvResult);

      btnAdd.setOnClickListener(this);
      btnSub.setOnClickListener(this);
      btnMult.setOnClickListener(this);
      btnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float num1 = 0;
        float num2 = 0;
        float rezult = 0;

        //check field on empty
        if(TextUtils.isEmpty(etNum1.getText().toString())
        || TextUtils.isEmpty(etNum2.getText().toString())){
            return;
        }

        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        //define necessery operation, and write on oper
        switch (v.getId()){
            case R.id.btnAdd:
                oper = "+";
                rezult = num1+num2;
                break;
            case R.id.btnSub:
                oper = "-";
                rezult = num1-num2;
                break;
            case R.id.btnMult:
                oper = "*";
                rezult = num1*num2;
                break;
            case R.id.btnDiv:
                oper = "/";
                rezult = num1/num2;
             default:
                 break;
        }
           //write output line
        tvResult.setText(num1+" "+oper+" "+num2+" = "+rezult);
    }

    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,MENU_RESET_ID,0,"Reset");
        menu.add(0,MENU_QUIT_ID,0,"Quit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_RESET_ID:
                //clear fields
                etNum1.setText("");
                etNum2.setText("");
                tvResult.setText("");
                break;
            case MENU_QUIT_ID:
                //Quit
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

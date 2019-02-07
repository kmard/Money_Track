package com.example.p0171_dynamiclayout2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llMain;
    RadioGroup rgGravity;
    EditText etName;
    Button btnCreate;
    Button btnClear;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llMain = (LinearLayout) findViewById(R.id.llMain);
        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        etName = (EditText) findViewById(R.id.etName);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnCreate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreate:
                //Create LayoutParams with width and height on content
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(wrapContent,wrapContent);
                //value for save align on default Left
                int btnGravity = Gravity.LEFT;
                //slect checked button and fill btnGravity
                  switch (rgGravity.getCheckedRadioButtonId()){
                      case R.id.rbLeft:
                          btnGravity = Gravity.LEFT;
                          break;
                      case R.id.rbCenter:
                          btnGravity = Gravity.CENTER_HORIZONTAL;
                          break;
                      case R.id.rbRight:
                          btnGravity = Gravity.RIGHT;
                          break;
                  }
                  //insert value of btnGravity in LinearLayout
                lParams.gravity = btnGravity;
                //Create Button, write text and add into LinearLayout
                Button btnNew = new Button(this);
                btnNew.setText(etName.getText().toString());
                llMain.addView(btnNew,lParams);
                break;
            case R.id.btnClear:
                llMain.removeAllViews();
                Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

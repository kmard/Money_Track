package com.example.sp_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnOk,btnCancel ;
    EditText edText;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        edText = (EditText) findViewById(R.id.UserName);
        tv = (TextView) findViewById(R.id.label);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOk:
                tv.setText(edText.getText().toString());
                break;
            case R.id.btnCancel:
                tv.setText("");
                break;
        }

    }
}

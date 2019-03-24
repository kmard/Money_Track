package com.example.p000g;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        EditText text_from_user = (EditText) findViewById(R.id.editText);
        TextView result = (TextView) findViewById(R.id.tvResult);

        if (text_from_user.getText().toString().matches("")) {
            result.setText("");
        } else {
            float num = Float.parseFloat(text_from_user.getText().toString());
            num *= 1.6;
            result.setText(Float.toString(num));
        }
    }

}

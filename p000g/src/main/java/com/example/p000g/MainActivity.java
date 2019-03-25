package com.example.p000g;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText text_from_user;
    private TextView result;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButtonClick();
    }


    public void onButtonClick() {
        this.text_from_user = findViewById(R.id.editText);
        this.result = findViewById(R.id.tvResult);
        this.btn = findViewById(R.id.btnConvert);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text_from_user.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Enter number !", Toast.LENGTH_SHORT).show();
                } else {

                    float num = Float.parseFloat(text_from_user.getText().toString());
                    num *= 1.6;
                    result.setText(Float.toString(num));
                    btn.setText("Ok ");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        btn.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    } else {
                        btn.setBackgroundColor(Color.RED);
                    }
                }
            }
        });
    }
}

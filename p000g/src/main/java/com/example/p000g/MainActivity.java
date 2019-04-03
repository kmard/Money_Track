package com.example.p000g;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText text_from_user;
    private TextView result;
    private Button btn;
    private FloatingActionButton floatBtn;
    private int count = 1;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButtonClick();

        img = (ImageView) findViewById(R.id.imgFood);

    }


    public void onButtonClick() {
        this.text_from_user = findViewById(R.id.editText);
        this.result = findViewById(R.id.tvResult);
        this.btn = findViewById(R.id.btnConvert);
        this.floatBtn = findViewById(R.id.floatBtn);

        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.p000g.LoginPageActivity");
                startActivity(intent);
            }
        });


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

    public void onClickBtn(View view) {
        count++;
        if (count > 4)
            count = 1;
        switch (count) {
            case 1:
                img.setImageResource(R.drawable.food_1);
                break;
            case 2:
                img.setImageResource(R.drawable.food_2);
                break;
            case 3:
                img.setImageResource(R.drawable.food_3);
                break;
            case 4:
                img.setImageResource(R.drawable.food_4);
                break;
        }
    }

}

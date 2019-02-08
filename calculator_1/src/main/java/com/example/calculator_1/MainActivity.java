package com.example.calculator_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOut;
    Button num1,num2,num3,num4,num5,num6,num7,num8,num9,num0,numComa,reverse,sum,sub,mult,div,cancel,eiqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut);
        num1 = (Button) findViewById(R.id.num1);
        num2 = (Button) findViewById(R.id.num2);
        num3 = (Button) findViewById(R.id.num3);
        num4 = (Button) findViewById(R.id.num4);
        num5 = (Button) findViewById(R.id.num5);
        num6 = (Button) findViewById(R.id.num6);
        num7 = (Button) findViewById(R.id.num7);
        num8 = (Button) findViewById(R.id.num8);
        num9 = (Button) findViewById(R.id.num9);
        num0 = (Button) findViewById(R.id.num0);
        numComa = (Button) findViewById(R.id.numComa);
        reverse = (Button) findViewById(R.id.reverse);
        sum = (Button) findViewById(R.id.sum);
        sub = (Button) findViewById(R.id.sub);
        mult = (Button) findViewById(R.id.mult);
        div = (Button) findViewById(R.id.div);
        cancel = (Button) findViewById(R.id.cancel);
        eiqual = (Button) findViewById(R.id.eiqual);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.num0:
                        tvOut.setText("0");
                        break;
                    case R.id.num1:
                        tvOut.setText("1");
                        break;
                    case R.id.num2:
                        tvOut.setText("2");
                        break;
                    case R.id.num3:
                        tvOut.setText("3");
                        break;
                    case R.id.num4:
                        tvOut.setText("4");
                        break;
                    case R.id.num5:
                        tvOut.setText("5");
                        break;
                    case R.id.num6:
                        tvOut.setText("6");
                        break;
                    case R.id.num7:
                        tvOut.setText("7");
                        break;
                    case R.id.num8:
                        tvOut.setText("8");
                        break;
                    case R.id.num9:
                        tvOut.setText("9");
                        break;
                }
            }
        };

        num0.setOnClickListener(onClickListener);
        num1.setOnClickListener(onClickListener);
        num2.setOnClickListener(onClickListener);
        num3.setOnClickListener(onClickListener);
        num4.setOnClickListener(onClickListener);
        num5.setOnClickListener(onClickListener);
        num6.setOnClickListener(onClickListener);
        num7.setOnClickListener(onClickListener);
        num8.setOnClickListener(onClickListener);
        num9.setOnClickListener(onClickListener);

    }
}

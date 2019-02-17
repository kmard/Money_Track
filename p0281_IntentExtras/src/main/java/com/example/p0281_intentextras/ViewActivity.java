package com.example.p0281_intentextras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity  implements View.OnClickListener {

    TextView tvView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvView = (TextView) findViewById(R.id.tvView);
        btnBack = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();

        String fname = intent.getStringExtra("fname");
        String lname = intent.getStringExtra("lname");

        tvView.setText("Your name is "+fname+" your last name is "+lname);

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}

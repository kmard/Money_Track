package com.example.twoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener {
     Button btn;
    private static final String TAG = "TwoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        Log.i(TAG, "onCreate: Second activity");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                Log.i(TAG, "onClick: return MainActivity");   
                break;
             default:

               break;
        }
    }
}

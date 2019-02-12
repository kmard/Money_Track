package com.example.twoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button  btnActTwo;
    private static final String TAG = "TwoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActTwo = (Button) findViewById(R.id.btnActivityTwo);
        btnActTwo.setOnClickListener(this);
        Log.i(TAG, "onCreate: mainActivity");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActivityTwo:
                Intent intent = new Intent(this,ActivityTwo.class);
                startActivity(intent);
                Log.i(TAG, "onClick: return Second activity");
                break;
              default:

               break;
        }
    }
}

package com.example.p0301_activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    final  int REQUEST_CODE_COLOR = 1;
    final  int REQUEST_CODE_ALIGN = 2;
    private static final String TAG = "myLog";


    TextView tvText;
    Button  btnColor;
    Button btnAlign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.tvText);
        btnColor = (Button) findViewById(R.id.btnColor);
        btnAlign = (Button) findViewById(R.id.btnAlign);

        btnColor.setOnClickListener(this);
        btnAlign.setOnClickListener( this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.btnColor:
                intent = new Intent(this, color.class);
                startActivityForResult(intent,REQUEST_CODE_COLOR);
                break;
            case R.id.btnAlign:
                intent = new Intent(this, align.class);
                startActivityForResult(intent,REQUEST_CODE_ALIGN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //write to log value requestCode b resultCode
        Log.i(TAG, "requestCode "+requestCode+" , resultCode " +resultCode);
        //if it's ok
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_COLOR:
                    int color = data.getIntExtra("color",Color.WHITE);
                    tvText.setTextColor(color);
                    break;
                case REQUEST_CODE_ALIGN:
                    int align = data.getIntExtra("alignment", Gravity.LEFT);
                    tvText.setGravity(align);
                    break;
            }
        }
        else {
            //if not ok
            Toast.makeText(this,"Wrong result",Toast.LENGTH_SHORT).show();}
    }
}

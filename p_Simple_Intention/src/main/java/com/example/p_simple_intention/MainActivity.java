package com.example.p_simple_intention;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     static final int CODE_SELECTED = 1;
    TextView mSelectedStation;
    Button btnPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         mSelectedStation = (TextView) findViewById(R.id.selectedStation);
          btnPress = (Button) findViewById(R.id.btnPress);
          btnPress.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,list_view.class);
        startActivityForResult(intent,CODE_SELECTED);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_SELECTED){
            if(resultCode == RESULT_OK){
                mSelectedStation.setText(data.getStringExtra(list_view.EXTRA_SELECTED_STATION));
            } else
            {
                mSelectedStation.setText("Unknown data");
            }
        } else{

        }

    }
}

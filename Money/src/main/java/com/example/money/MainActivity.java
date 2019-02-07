package com.example.money;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        Toast.makeText(this,"onStop()",Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this,"onRestart()",Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this,"onResume()",Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this,"onPause()",Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this,"onDestroy()",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}

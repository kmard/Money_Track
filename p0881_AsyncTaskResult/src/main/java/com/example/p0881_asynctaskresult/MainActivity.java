package com.example.p0881_asynctaskresult;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    myTask mt;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tvInfo);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mt = new myTask();
                mt.execute();
                break;
            case R.id.btnGet:
                showResult();
                break;
            default:
                break;
        }
    }


    private void showResult() {
        if(mt == null) return;
        int result = -1;
        try{
            Log.i(TAG, "Try to get result ");
            result = mt.get();
            Log.i(TAG, "get returns "+result);
            Toast.makeText(this,"get returns "+result,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

     class myTask extends AsyncTask<Void,Void,Integer> {

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             tvInfo.setText("Begin");
             Log.i(TAG, "Begin ");
         }

         @Override
         protected Integer doInBackground(Void... params) {
             try{
                 TimeUnit.SECONDS.sleep(5);
             } catch( Exception e){
                 e.printStackTrace();
             }
             return 100500;
         }

         @Override
         protected void onPostExecute(Integer result) {
             super.onPostExecute(result);
             tvInfo.setText("End. Result = "+result);
             Log.i(TAG, "End. Result = "+result);
         }
     }

}

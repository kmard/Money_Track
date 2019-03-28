package com.example.p0751_files;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    private final String DIR_SD = "MyFiles";
    private final String FILENAME_SD = "fileSD";
    private String FILENAME = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnWrite:
                writeFile();
                break;
            case R.id.btnRead:
                readFile();
                break;
            case R.id.btnWriteSD:
                writeFileSD();
                break;
            case R.id.btnReadSD:
                readFileSD();
                break;
        }
    }


    private void writeFile() {
        try {
            //open threrad for writing
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_PRIVATE)));
            //write data
            bw.write("some text for file");
            //close thread
            bw.close();
            Log.i(TAG, "File was recorded ");
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    private void readFile() {

        try {
            //open thread for reading
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            String str = "";
            //read file
            while ((str = br.readLine()) != null) {
                Log.i(TAG, "read File : str " + str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFileSD() {
        //check availability SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i(TAG, "SD card is not available ");
            return;
        }

        //receive path to the SD
        File sdPath = Environment.getExternalStorageDirectory();
        //add our catalog to path
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        //create catalog
        sdPath.mkdirs();
        //form the object file
        File sdFile = new File(sdPath, FILENAME_SD);
        Log.i(TAG, "Writinf on SD card  ");
        try {
            //open thread for writing
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));

            //write data
            bw.write("some text for SD");

            //close thread
            bw.close();
            Log.i(TAG, "writeFileSD:  File was recorded");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFileSD() {
        //check available SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i(TAG, "SD card is not available : " + Environment.getExternalStorageState());
            return;
        }
        //receive path to the SD
        File sdPath = Environment.getExternalStorageDirectory();
        //add catalog th the Path
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        //create object File with path to the file
        File sdFile = new File(sdPath, FILENAME_SD);

        try {
            //open thread
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            //read it
            while ((str = br.readLine()) != null) {
                Log.i(TAG, "readFileSD: " + str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

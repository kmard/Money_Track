package com.example.p0681_parcel;

import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    Parcel p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeParcel();
        readParsel();
    }

    private void readParsel() {
        logReadInfo("before reading");
        p.setDataPosition(0);
        logReadInfo("byte =  " + p.readByte());
        logReadInfo("int = " + p.readInt());
        logReadInfo("long = " + p.readLong());
        logReadInfo("float = " + p.readFloat());
        logReadInfo("double = " + p.readDouble());
        logReadInfo("string = " + p.readString());
    }

    private void logReadInfo(String txt) {
        Log.i(TAG, txt + ": " + "dataPosition = " + p.dataPosition());
    }

    private void writeParcel() {

        p = Parcel.obtain();

        byte b = 1;
        int i = 2;
        long l = 3;
        float f = 4;
        double d = 5;
        String s = "abcdefgh";

        logWriteInfo("Before writing");
        p.writeByte(b);
        logWriteInfo("byte");
        p.writeInt(i);
        logWriteInfo("int");
        p.writeLong(i);
        logWriteInfo("Long");
        p.writeFloat(f);
        logWriteInfo("Float");
        p.writeDouble(d);
        logWriteInfo("Double");
        p.writeString(s);
        logWriteInfo("String");

    }

    private void logWriteInfo(String txt) {
        Log.i(TAG, txt + ":  " + "dataSize = " + p.dataSize());
    }
}

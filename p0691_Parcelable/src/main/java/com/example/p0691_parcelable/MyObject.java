package com.example.p0691_parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class MyObject implements Parcelable {

    public static final Creator<MyObject> CREATOR = new Creator<MyObject>() {
        @Override
        public MyObject createFromParcel(Parcel in) {
            return new MyObject(in);
        }

        @Override
        public MyObject[] newArray(int size) {
            return new MyObject[size];
        }
    };
    private static final String TAG = "myLogs";
    public String s;
    public int i;


    // simple constarctor
    public MyObject(String _s, int _i) {
        Log.d(TAG, "MyObject(String _s, int _i)");
        s = _s;
        i = _i;
    }

    // constractor that reading data from Parcel
    private MyObject(Parcel parcel) {
        Log.d(TAG, "MyObject(Parcel parcel)");
        s = parcel.readString();
        i = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    // pack object to Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        Log.d(TAG, "writeToParcel");
        parcel.writeString(s);
        parcel.writeInt(i);
    }


}

package com.example.p0381_sqlitetransaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

//http://www.enterra.ru/blog/android_issues_with_sqlite/
//https://www.ibm.com/developerworks/ru/library/x-andbene1/index.html

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "myLogs";

    DBHelper dbh;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "--- onCreate Activity ---");
        dbh = new DBHelper(this);
        myAction();
    }

     void myAction() {
        try {
        db = dbh.getWritableDatabase();
        delete(db,"mytable");

        db.beginTransaction();
        insert(db,"mytable","val1");

        db.setTransactionSuccessful();
         insert(db,"mytable","val2");
         db.endTransaction();
         insert(db,"mytable","val3");
        read(db,"mytable");
        dbh.close();
        } catch (Exception ex){
            Log.i(LOG_TAG, ex.getClass() + " error: " + ex.getMessage());
        }

    }

    void read(SQLiteDatabase db,String table){
        Log.i(LOG_TAG, "Read table " + table);
        Cursor c = db.query(table,null,null,null,null,null,null);
        if(c != null){
            Log.i(LOG_TAG, "Records count = " + c.getCount());
            if (c.moveToFirst()) {
                do {
                    Log.i(LOG_TAG, c.getString(c.getColumnIndex("val")));
                } while (c.moveToNext());
            }
            c.close();
        }
    }

    void insert(SQLiteDatabase db,String table,String value){
        Log.i(LOG_TAG, "Insert in table " + table + " value = " + value);
        ContentValues cv = new ContentValues();
        cv.put("val",value);
        db.insert(table,null,cv);
    }

    void delete(SQLiteDatabase db,String table){
        Log.i(LOG_TAG, "Delete all from table " + table);
        db.delete(table,null,null);
    }

    //class for work with DB
     class DBHelper extends SQLiteOpenHelper {

        public DBHelper( Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(LOG_TAG, "--- onCreate database ---");
            db.execSQL("create table mytable("
            +"id integer primary key autoincrement,"
            +"val text"
            +");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

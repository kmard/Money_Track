package com.example.p0631_alertdialogitemssingle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TXT = "txt";
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";
    private static final String DB_CREATE =
            "create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key, " +
                    COLUMN_TXT + " text" +
                    ");";

    private final Context mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;


    public DB(Context Ctx) {
        this.mCtx = Ctx;
    }

    //open connection
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    //close connection
    public void close() {
        if (mDBHelper != null) mDBHelper.close();
    }

    //get data from table DB_TABLE
    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }

    //class DBHelper
    private class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Create and fill BD
            db.execSQL(DB_CREATE);

            ContentValues cv = new ContentValues();

            for (int i = 1; i < 5; i++) {
                cv.put(COLUMN_ID, i);
                cv.put(COLUMN_TXT, "Name " + i);
                db.insert(DB_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

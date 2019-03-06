package com.example.p0521_simplecursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DB {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_TXT = "txt";
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";
    private static final String DB_CREATE =
            "create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_IMG + " integer, " +
                    COLUMN_TXT + " text" + ");";

    private final Context mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;


    public DB(Context ctx) {
        this.mCtx = ctx;
    }

    //open connection
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    //close connection
    public void close() {
        if (mDBHelper != null) {
            mDBHelper.close();
        }
    }

    //give all string from table DB_TABLE
    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }

    //add record to DB_TABLE
    public void addRec(String txt, int img) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TXT, txt);
        cv.put(COLUMN_IMG, img);
        mDB.insert(DB_TABLE, null, cv);
    }

    //delete record from DB_TABLE
    public void delRec(long id) {
        mDB.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
    }


    //class for create and manage BD
    private class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //create and fill in
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);

            ContentValues cv = new ContentValues();
            for (int i = 1; i < 5; i++) {
                cv.put(COLUMN_TXT, "sometext " + i);
                cv.put(COLUMN_IMG, R.mipmap.ic_launcher_round);
                db.insert(DB_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

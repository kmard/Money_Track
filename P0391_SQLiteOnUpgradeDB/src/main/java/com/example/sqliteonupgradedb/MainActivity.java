package com.example.sqliteonupgradedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "myLogs";

    //name BD
    final String DB_NAME = "staff";
    //version BD
    final int DB_VERSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        Log.d(LOG_TAG, " --- Staff db v." + db.getVersion() + " --- ");
        writeStaff(db);
        dbh.close();
    }

    //Query for data and uotput to the log
    private void writeStaff(SQLiteDatabase db) {
        Cursor c = db.rawQuery("Select * from people",null);
        logCursor(c,"Table people");
        c.close();

       String sglQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
               + "from people as PL "
               +"inner join position as PS "
               +"on PL.posid = PS.id" ;
       c = db.rawQuery(sglQuery,null);
       logCursor(c,"inner join");
       c.close();
    }

    private void logCursor(Cursor c, String title) {
        if(c != null){
             if(c.moveToFirst()){
                 Log.d(LOG_TAG,title + ". "+c.getCount()+ " rows");
                 StringBuilder sb = new StringBuilder();
                 do {
                      sb.setLength(0);
                      for (String cn : c.getColumnNames()){
                          sb.append(cn+" = "+c.getString(c.getColumnIndex(cn))+"; ");
                      }
                      Log.d(LOG_TAG, sb.toString());
                 }while(c.moveToNext());
             }
        } else{
            Log.d(LOG_TAG, title + ". Cursor is null");
        }
    }


    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context  context) {
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, " --- onCreate database --- ");

            String[] people_name  = {"Ivan","Mary","Petr","Anton","Dasha","Boris","Kostya","Igor"};
            String[] people_posid = {"Programmer","Bux","Programmer","Programmer","Bux","Chief","Programmer","Security"};

            //value for table vacation
            int[] position_id = {1,2,3,4};
            String[] position_name = {"Chief","Programmer","Bux","Security"};
            int[] position_salary = {15000,13000,10000,8000};

            ContentValues cv = new ContentValues();

            //Create table position
            db.execSQL("create table position("+"id integer primary key,"
            +"name text, salary integer"+");");

            //fill it
            for (int i = 0; i < position_id.length; i++){
                cv.clear();
                cv.put("id",position_id[i]);
                cv.put("name",position_name[i]);
                cv.put("salary",position_salary[i]);
                db.insert("position",null,cv);
            }

            //Create table for name people
            db.execSQL("create table people ("
              +"id integer primary key autoincrement,"
              +"name text, posid integer);");

            //fill this table
            for (int i = 0; i<people_name.length;i++){
                cv.clear();
                cv.put("name",people_name[i]);
                cv.put("position",people_posid[i]);
                db.insert("people",null,cv);

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(LOG_TAG, " --- onUpgrade database from " + oldVersion
                    + " to " + newVersion + " version --- ");

            if(oldVersion ==1 && newVersion==2){
                ContentValues cv = new ContentValues();

                //data for table position
                int[] position_id = {1,2,3,4,};
                String[] position_name = {"Chief","Programmer","Bux","Security"};
                int[] position_salary = {15000,13000,10000,8000};

                db.beginTransaction();
                try {
                    //create table vocation
                    db.execSQL("create table position ("
                            +"id integer primary key,"
                            +"name text, salary integer);");

                    //fill it
                    for(int i = 0;i<position_id.length;i++){
                        cv.clear();
                        cv.put("id",position_id[i]);
                        cv.put("name",position_name[i]);
                        cv.put("salary",position_salary[i]);
                        db.insert("position",null,cv);
                    }

                    db.execSQL("alter table people add column posid integer");

                    for (int i=0;i < position_id.length;i++){

                        cv.clear();
                        cv.put("posid",position_id[i]);
                        db.update("people",cv,"position = ?",new String[]{position_name[i]});
                    }

                    db.execSQL("create temporary table people_tmp("
                    +"id integer,name text,position text,posid integer);");

                    db.execSQL("insert into people_tmp select id,name,position,posid from people;");
                    db.execSQL("drop table people;");

                    db.execSQL("create table people("
                    +"id integer primary key autoincrement,"
                    +"name text, posid integer);");

                    db.execSQL("insert into people select id, name, posid from people_tmp;");
                    db.execSQL("drop table people_tmp;");

                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

            }
        }
    }
}

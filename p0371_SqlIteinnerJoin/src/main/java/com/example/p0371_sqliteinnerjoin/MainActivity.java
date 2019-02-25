package com.example.p0371_sqliteinnerjoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    //data for table vacancies
    int [] position_id             = {1,2,3,4};
    String [] position_name = {"Director","Programmer","Bux","Security"};
    int [] position_salary = {15000,13000,10000,8000};

    //data for table people
    String [] people_name = {"Ivan","Marry","Petr","Anton","Dasha","Boris","Kostya","Igor"};
    int [] people_posid          = {2,3,2,2,3,1,2,4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connecting to BD
        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();

        //decription cursor
        Cursor c;

        //outPut to the log data for vacancies
        Log.i(TAG, "----Table position----- ");
        c  = db.query("position",null,null,null,null,null,null);
        logCursor(c);
        c.close();
        Log.i(TAG, "-------    -------");

        //outPut to the log data for people
        Log.i(TAG, "----Table people----- ");
        c  = db.query("people",null,null,null,null,null,null);
        logCursor(c);
        c.close();
        Log.i(TAG, "-------    -------");

        //outPut rezult union
        //uses rawQuery
        Log.i(TAG, "---Inner JOIN with rawQuery---");
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?";
           c = db.rawQuery(sqlQuery,new String[]{"12000"});
        logCursor(c);
        c.close();
        Log.i(TAG, "---      ----");

        //outPut result UNION
       // use query
        Log.i(TAG, "--- INNER JOIN with query---");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String columns[] = { "PL.name as Name", "PS.name as Position", "salary as Salary" };
        String selection = "salary < ?";
        String[] selectionArgs = {"12000"};
        c = db.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(c);
        c.close();
        Log.i(TAG, "--- ---");

        //close connection
        dbh.close();

    }

     void logCursor(Cursor c) {
        if (c != null){
            if(c.moveToFirst()){
                String str;
                do{
                    str = "";
                    for (String cn : c.getColumnNames()){
                        str = str.concat(cn+" = "+c.getString(c.getColumnIndex(cn))+";");
                    }
                    Log.i(TAG, str);
                } while (c.moveToNext());
            }
        }else{
            Log.i(TAG, "Cursor is Null");
        }
    }


    //Class for with DB
     class DBHelper extends SQLiteOpenHelper {

         public DBHelper(Context context) {
             super(context, "myDB", null, 1);
         }

         @Override
         public void onCreate(SQLiteDatabase db) {
             Log.i(TAG, "------onCreate: DataBase----- ");

             ContentValues cv = new ContentValues();

             //create table vacancies
             db.execSQL("create table position("
                     +"id integer primary key,"
                     +"name text,"+"salary integer"
                     +");");

             //fill  table vacancies
             for(int i = 0 ;i<position_id.length;i++){
                 cv.clear();
                 cv.put("id",position_id[i]);
                 cv.put("name",position_name[i]);
                 cv.put("salary",position_salary[i]);
                 db.insert("position",null, cv);

             }

             //create table people
             db.execSQL("create table people("
                     +"id integer primary key autoincrement,"
                     +"name text,"
                     +"posid integer"
                     +");");

             //fill  table people
             for(int i = 0; i<people_name.length;i++) {
                 cv.clear();
                 cv.put("name", people_name[i]);
                 cv.put("posid", people_posid[i]);
                 db.insert("people", null, cv);
             }
         }

         @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

         }
     }
}

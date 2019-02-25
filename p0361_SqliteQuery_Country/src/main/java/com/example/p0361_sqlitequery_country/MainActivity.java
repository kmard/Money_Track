package com.example.p0361_sqlitequery_country;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myLogs";

    String name[] = {"China","USA","Brazil","Russia","Japan","Germany","Egypt","Italy","France","Canada"};

    int people[] = {1400,311,195,142,128,82,80,60,66,35};

    String region[] = {"Asia","America","America","Europe","Asia","Europe","Africa","Europe","Europe","America"};

    Button btnAll,btnFunc,btnPeople,btnSort,btnGroup,btnHaving;

    EditText etFunc, etPeople, etRegionPeople;

    RadioGroup rgSort;

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(this);

        btnPeople = (Button) findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(this);

        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);

        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);

        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnHaving.setOnClickListener(this);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPeople = (EditText) findViewById(R.id.etPeople);
        etRegionPeople = (EditText) findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);

        //connect to the DB
        db = dbHelper.getWritableDatabase();

        //check for existing records
        Cursor c = db.query("mytable",null,null,null,null,null,null);

        if(c.getCount() == 0){
            ContentValues cv = new ContentValues();
            //fill the table
            for (int i= 0; i < 10; i++){
                cv.put("name",name[i]);
                cv.put("people",people[i]);
                cv.put("region",region[i]);
                Log.i(TAG, "id = "+db.insert("mytable",null,cv));
            }
        }
        c.close();
        dbHelper.close();

//        //emulate pressing button all
//        onClick(btnAll);
    }

    @Override
    public void onClick(View v) {

        //connecting yo the DB
        db = dbHelper.getWritableDatabase();

        //data for the screen
        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionalPeople = etRegionPeople.getText().toString();

        //value for query
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        //cursor
        Cursor c = null;

        //find pressed button
        switch (v.getId()){
             //all records
            case R.id.btnAll:
                Log.i(TAG, "------All records----------");
                c = db.query("mytable",null,null,null,null,null,null);
                break;
             //function
            case R.id.btnFunc:
                Log.i(TAG, "------ Func :"+sFunc+" ----- ");
                columns = new String[] {sFunc};
                c = db.query("mytable",columns,null,null,null,null,null);
                break;
               //Population better then
            case R.id.btnPeople:
                Log.i(TAG, "------ population beter then  :"+sPeople+" ----- ");
                selection = "people > ?";
                selectionArgs = new String[] {sPeople};
                c = db.query("mytable",null,selection,selectionArgs,null,null,null);
                break;
                //Population of the region
            case R.id.btnGroup:
                    Log.i(TAG, "------ Population on region   ----- ");
                     columns = new String[] {"region","sum(people) as people"};
                     groupBy = "region";
                    c = db.query("mytable",columns,null,null,groupBy,null,null);
                    break;
               //Population of region bigger then
            case R.id.btnHaving :
                Log.i(TAG, "------ Population of region bigger then   ----- ");
                columns = new String[] {"region","sum(people) as people"};
                groupBy = "region";
                having = "sum(people) > "+sRegionalPeople;
                c = db.query("mytable",columns,null,null,groupBy,having,null);
                break;
                //sort
            case R.id.btnSort:
                //sort by
                switch (rgSort.getCheckedRadioButtonId()){
                     //sort by name
                    case R.id.rName:
                        Log.i(TAG, "------ sort by name   ----- ");
                        orderBy = "name";
                        break;
                      //sort by population
                    case R.id.rPeople:
                        Log.i(TAG, "------ sort by population   ----- ");
                        orderBy = "people";
                        break;
                    //sort by region
                    case R.id.rRegion:
                        Log.i(TAG, "------ sort by region   ----- ");
                        orderBy = "region";
                        break;
                }
                c = db.query("mytable",null,null,null,null,null,orderBy);
                break;
        }

        if(c != null){
            if(c.moveToFirst()){
                String str;
                do{
                    str = "";
                    for (String cn : c.getColumnNames()){
                        str = str.concat(cn+" = "
                        +c.getString(c.getColumnIndex(cn))+";");
                    }
                    Log.i(TAG, str);
                } while(c.moveToNext());
            }
            c.close();
        } else{
            Log.i(TAG, "Cursor is null ");
        }
        dbHelper.close();
    }

     class DBHelper extends SQLiteOpenHelper {

         public DBHelper( Context context) {
             //constractor super class
             super(context, "myDB",null,1);
         }

         @Override
        public void onCreate(SQLiteDatabase db) {
             Log.i(TAG, "----Create DB --------- ");
             //create table with columns
             db.execSQL("create table mytable ("
                     +"id integer primary key autoincrement,"+"name text,"
                     +"people integer,"+"region text"+");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

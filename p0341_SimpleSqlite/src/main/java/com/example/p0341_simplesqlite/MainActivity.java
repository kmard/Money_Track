package com.example.p0341_simplesqlite;

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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myLogs";

    Button btnAdd,btnRead,btnClear;
    EditText etName,etEmail;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);

        //Create object for management version BD
        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {

        //create object for data
        ContentValues cv = new ContentValues();

        //receive data for fields (enter)
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        //connect to DB
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()){

            case R.id.btnAdd:
                Log.i(TAG, "-----Insert on mutable-------");
                //prepare value for insert in case : name column - value
                cv.put("name",name);
                cv.put("email",email);

                //put the record and receive it on ID
                long rowID = db.insert("mytable",null,cv);
                Log.i(TAG, " row inserted, ID = " + rowID);
                break;

            case R.id.btnRead:
                Log.i(TAG, "-----Rows in my table-----");
                //doing query all value from the table mytable , get the cursor
                Cursor c  = db.query("mytable",null,null,null,null,null,null);

                //Put position's cursor in the first string selection
                //if  selection don't have string then return false
                if(c.moveToFirst()){
                      //select number colums for name in the selection
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex= c.getColumnIndex("email");

                    do {
                        //recieve value on the number column and write to the log
                        Log.i(TAG,
                                "ID = "+c.getInt(idColIndex)+
                                ", name = "+c.getString(nameColIndex)+
                                " ,email = "+c.getString(emailColIndex));
                    }while (c.moveToNext());
                } else{
                    Log.i(TAG, " 0 rows");
                    c.close();
                    break;
                }
            case R.id.btnClear:
                Log.i(TAG, "----Clear mytable : --------");
                //delete all records
                int clearCount = db.delete("mytable",null,null);
                Log.i(TAG,"deleted rows count = "+clearCount);
                break;
        }
        //close connection with DB
        dbHelper.close();
    }


     class DBHelper  extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            //constractor super class's
            super(context,"myDB",null,1);
        }

         @Override
         public void onCreate(SQLiteDatabase db) {
             Log.i(TAG, "-----onCreateDB-------");
             //create table with fields
             db.execSQL("create table mytable ("
                     +"id integer primary key autoincrement,"
                     +"name, text,"
                     +"email text"+");");
         }

         @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

         }
     }
}

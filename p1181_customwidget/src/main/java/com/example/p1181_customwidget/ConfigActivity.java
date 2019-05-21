package com.example.p1181_customwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ConfigActivity extends AppCompatActivity {

    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TEXT = "widget_text_";
    public final static String Widget_COLOR = "widget_color_";
    private static final String TAG = "myLogs";
    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: config ");

        //get id widget
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        //check for correctly
        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        //create intent answer
        resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        //negative answer
        setResult(RESULT_CANCELED, resultValue);

        setContentView(R.layout.config);
    }


    public void onClick(View v) {
        int selRBColor = ((RadioGroup) findViewById(R.id.rgColor)).getCheckedRadioButtonId();
        int color = Color.RED;
        switch (selRBColor) {
            case R.id.radioRed:
                color = Color.parseColor("#66ff0000");
                break;
            case R.id.radioGreen:
                color = Color.parseColor("#6600ff00");
                break;
            case R.id.radioBlue:
                color = Color.parseColor("#660000ff");
                break;
        }
        EditText etText = (EditText) findViewById(R.id.etText);

        //Write value from screen in Preferences
        SharedPreferences sp = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(WIDGET_TEXT + widgetID, etText.getText().toString());
        editor.putInt(Widget_COLOR + widgetID, color);
        editor.commit();

        //positive answer
        setResult(RESULT_OK, resultValue);

        Log.i(TAG, "finish cinfig " + widgetID);
        finish();
    }
}

package com.example.p1201_clickwidget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ConfigActivity extends AppCompatActivity {

    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TIME_FORMAT = "widget_time_format_";
    public final static String WIDGET_COUNT = "widget_count_";

    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;
    SharedPreferences sp;
    EditText etFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_config);

        //put ID config widget
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        //check correct
        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        //create intent replay
        resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        //negative replay
        setResult(RESULT_CANCELED, resultValue);

        setContentView(R.layout.activity_config);

        sp = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        etFormat = (EditText) findViewById(R.id.etFormat);
        etFormat.setText(sp.getString(WIDGET_TIME_FORMAT + widgetID, "HH:mm:ss"));

        int cnt = sp.getInt(ConfigActivity.WIDGET_COUNT + widgetID, -1);
        if (cnt == 1) sp.edit().putInt(WIDGET_COUNT + widgetID, 0);
    }

    public void onClick(View view) {
        sp.edit().putString(WIDGET_TIME_FORMAT + widgetID, etFormat.getText().toString()).commit();

        setResult(RESULT_OK, resultValue);
        finish();
    }
}

package com.example.p1181_customwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Arrays;

public class MyWidget extends AppWidgetProvider {

    private static final String TAG = "myLogs";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i(TAG, "onEnabled: ");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate: " + Arrays.toString(appWidgetIds));

        SharedPreferences sp = context.getSharedPreferences(
                ConfigActivity.WIDGET_PREF, Context.MODE_PRIVATE);
        for (int id : appWidgetIds) {
            updateWidget(context, appWidgetManager, sp, id);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i(TAG, "onDeleted: " + Arrays.toString(appWidgetIds));

        //Delete Preferences
        SharedPreferences.Editor editor = context.getSharedPreferences(
                ConfigActivity.WIDGET_PREF, Context.MODE_PRIVATE).edit();
        for (int widgetID : appWidgetIds) {
            editor.remove(ConfigActivity.WIDGET_TEXT + widgetID);
            editor.remove(ConfigActivity.Widget_COLOR + widgetID);
        }
        editor.commit();
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.i(TAG, "onDisabled: ");
    }

    private void updateWidget(Context context, AppWidgetManager appWidgetManager, SharedPreferences sp, int widgetId) {
        Log.i(TAG, "updateWidget: " + widgetId);

        //Read parametrs Preferences
        String widgetText = sp.getString(ConfigActivity.WIDGET_TEXT + widgetId, null);
        if (widgetText == null) return;
        int widgetColor = sp.getInt(ConfigActivity.Widget_COLOR + widgetId, 0);

        //customize the look
        RemoteViews widgetView = new RemoteViews(context.getPackageName(),
                R.layout.activity_widget);
        widgetView.setTextViewText(R.id.tv, widgetText);
        widgetView.setInt(R.id.tv, "setBackgroundColor", widgetColor);

        //update widget
        appWidgetManager.updateAppWidget(widgetId, widgetView);
    }
}

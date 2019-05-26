package com.example.p1201_clickwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class widget extends AppWidgetProvider {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_widget);
//    }

    final static String ACTION_CHANGE = "com.example.p1201_clickwidget.change_count";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //update all instances
        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        //delete Preferences
        SharedPreferences.Editor editor = context.getSharedPreferences(
                ConfigActivity.WIDGET_PREF, Context.MODE_PRIVATE
        ).edit();
        for (int widgetID : appWidgetIds) {
            editor.remove(ConfigActivity.WIDGET_TIME_FORMAT + widgetID);
            editor.remove(ConfigActivity.WIDGET_COUNT + widgetID);
        }
        editor.commit();
    }

    private void updateWidget(Context ctx, AppWidgetManager appWidgetManager, int widgetID) {

        SharedPreferences sp = ctx.getSharedPreferences(ConfigActivity.WIDGET_PREF, Context.MODE_PRIVATE);

        //read format time and find current time
        String timeFormat = sp.getString(ConfigActivity.WIDGET_TIME_FORMAT + widgetID, null);

        if (timeFormat == null) return;
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        String currentTime = sdf.format(new Date(System.currentTimeMillis()));

        //read count
        String count = String.valueOf(sp.getInt(ConfigActivity.WIDGET_COUNT + widgetID, 0));

        //put data in text fields
        RemoteViews widgetView = new RemoteViews(ctx.getPackageName(), R.layout.activity_widget);
        widgetView.setTextViewText(R.id.tvTime, currentTime);
        widgetView.setTextViewText(R.id.tvCount, count);

        //config screen first zone
        Intent configIntent = new Intent(ctx, ConfigActivity.class);
        configIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, widgetID,
                configIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressConfig, pIntent);

        //updating widget (second zone)
        Intent updateIntent = new Intent(ctx, widget.class);
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                new int[] {widgetID});
        pIntent = PendingIntent.getBroadcast(ctx, widgetID, updateIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressUpdate, pIntent);

        //Count pressing (third zone)
        Intent countIntent = new Intent(ctx, widget.class);
        countIntent.setAction(ACTION_CHANGE);
        countIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        pIntent = PendingIntent.getBroadcast(ctx, widgetID, countIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.tvPressCount, pIntent);

        //update widget
        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        //check that it intent on pushing on third zone
        if (intent.getAction().equalsIgnoreCase(ACTION_CHANGE)) {

            //get ID instanse
            int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
            Bundle extras = intent.getExtras();
            if (extras != null) {
                mAppWidgetId = extras.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
            }
            if (mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                //Read count, and increase on one and record
                SharedPreferences sp = context.getSharedPreferences(
                        ConfigActivity.WIDGET_PREF, Context.MODE_PRIVATE);
                int cnt = sp.getInt(ConfigActivity.WIDGET_COUNT + mAppWidgetId, 0);
                sp.edit().putInt(ConfigActivity.WIDGET_COUNT + mAppWidgetId, ++cnt).commit();

                //update widget
                updateWidget(context, AppWidgetManager.getInstance(context), mAppWidgetId);
            }
        }
    }
}




























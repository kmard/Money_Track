package com.example.p1211_listwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MyProvider extends AppWidgetProvider {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int i : appWidgetIds) {
            updateWidget(context, appWidgetManager, i);
        }
    }

    void updateWidget(Context context, AppWidgetManager appWidgetManager, int AppWidgetId) {

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.activity_widget);

        setUpdateTV(rv, context, AppWidgetId);

        setList(rv, context, AppWidgetId);

        setListClick(rv, context, AppWidgetId);

        appWidgetManager.updateAppWidget(AppWidgetId, rv);
    }

    void setUpdateTV(RemoteViews rv, Context context, int appWidgetId) {

        rv.setTextViewText(R.id.tvUpdate, sdf.format(new Date(System.currentTimeMillis())));

        Intent updIntent = new Intent(context, MyProvider.class);

        updIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        updIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                new int[] {appWidgetId});

        PendingIntent updPIntent = PendingIntent.getBroadcast(context, appWidgetId, updIntent, 0);

        rv.setOnClickPendingIntent(R.id.tvUpdate, updPIntent);
    }

    void setList(RemoteViews rv, Context context, int appWidgetId) {

        Intent adapter = new Intent(context, MyService.class);
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        rv.setRemoteAdapter(R.id.lvList, adapter);
    }

    void setListClick(RemoteViews rv, Context context, int appWidgetId) {

    }

}

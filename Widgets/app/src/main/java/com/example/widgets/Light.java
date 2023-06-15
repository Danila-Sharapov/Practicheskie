package com.example.widgets;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class Light extends BroadcastReceiver {
    FlashClass flashClass;
    private boolean isSvett = false;
    private Context contextM;

    @Override
    public void onReceive(Context context, Intent intent) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_view);
        contextM = context.getApplicationContext();
        flashClass = new FlashClass(contextM);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(contextM);
        ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);

        Log.d("Light", String.valueOf(isSvett));

        if (isSvett)
        {
            views.setTextViewText(R.id.VKLVIKL, "VKL");
            flashClass.SvetOff();
            isSvett = false;
        }
        else
        {
            views.setTextViewText(R.id.VKLVIKL, "VIKL");
            flashClass.SvetOn();
            isSvett = true;
        }
        appWidgetManager.updateAppWidget(thisWidget, views);


    }
}

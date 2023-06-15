package com.example.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
    private boolean isSvet = false;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int appWidgetId : appWidgetIds){

            Intent receiver = new Intent(context, Light.class);
            receiver.setAction("COM_FLASHLIGHT");
            receiver.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            receiver.putExtra("isLight", isSvet);


            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, receiver, PendingIntent.FLAG_IMMUTABLE);
// Выше PendingIntent.FLAG_IMMUTABLE (проверить на паре)


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_view);
            views.setOnClickPendingIntent(R.id.VKLVIKL, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);


            if (isSvet) isSvet = false;

            else isSvet = true;

        }
    }

}

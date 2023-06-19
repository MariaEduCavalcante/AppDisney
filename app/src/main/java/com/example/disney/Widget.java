package com.example.disney;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Calendar;

public class Widget extends AppWidgetProvider  {

    public final static String EXTRA_MESSAGE = ".MESSAGE";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId: appWidgetIds) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);


            Calendar calendar = Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            switch (dayOfWeek) {
                case Calendar.MONDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.moana);
                    Intent intent = new Intent(context, pesquisa.class);
                    String message = "Moana";
                    intent.putExtra(EXTRA_MESSAGE, message);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent, 0);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent);
                    break;
                case Calendar.TUESDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.moana);
                    break;
                case Calendar.WEDNESDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.elsa);
                    break;
                case Calendar.THURSDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.moana);
                    break;
                case Calendar.FRIDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.elsa);
                    break;
                case Calendar.SATURDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.moana);
                    break;
                case Calendar.SUNDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.elsa);
                    Intent intent7 = new Intent(context, pesquisa.class);
                    String message7 = "Elsa";
                    intent7.putExtra(EXTRA_MESSAGE, message7);
                    PendingIntent pendingIntent7 = PendingIntent.getActivity(context,0, intent7, 0);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent7);
                    break;
                default:
                    break;
            }

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}

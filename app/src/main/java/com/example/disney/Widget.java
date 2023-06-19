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
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent);
                    break;
                case Calendar.TUESDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.bela);
                    Intent intent2 = new Intent(context, pesquisa.class);
                    String message2 = "Bela";
                    intent2.putExtra(EXTRA_MESSAGE, message2);
                    PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_IMMUTABLE);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent2);
                    break;
                case Calendar.WEDNESDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.rapunzel);
                    Intent intent3 = new Intent(context, pesquisa.class);
                    String message3 = "Rapunzel";
                    intent3.putExtra(EXTRA_MESSAGE, message3);
                    PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 0, intent3, PendingIntent.FLAG_IMMUTABLE);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent3);
                    break;
                case Calendar.THURSDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.cinderela);
                    Intent intent4 = new Intent(context, pesquisa.class);
                    String message4 = "Cinderella";
                    intent4.putExtra(EXTRA_MESSAGE, message4);
                    PendingIntent pendingIntent4 = PendingIntent.getActivity(context, 0, intent4, PendingIntent.FLAG_IMMUTABLE);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent4);
                    break;
                case Calendar.FRIDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.tiana);
                    Intent intent5 = new Intent(context, pesquisa.class);
                    String message5 = "Tiana";
                    intent5.putExtra(EXTRA_MESSAGE, message5);
                    PendingIntent pendingIntent5 = PendingIntent.getActivity(context, 0, intent5, PendingIntent.FLAG_IMMUTABLE);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent5);
                    break;
                case Calendar.SATURDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.jasmine);
                    Intent intent6 = new Intent(context, pesquisa.class);
                    String message6 = "Jasmine";
                    intent6.putExtra(EXTRA_MESSAGE, message6);
                    PendingIntent pendingIntent6 = PendingIntent.getActivity(context, 0, intent6, PendingIntent.FLAG_IMMUTABLE);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent6);
                    break;
                case Calendar.SUNDAY:
                    views.setImageViewResource(R.id.imgPersonaWidget, R.drawable.elsa);
                    Intent intent7 = new Intent(context, pesquisa.class);
                    String message7 = "Elsa";
                    intent7.putExtra(EXTRA_MESSAGE, message7);
                    PendingIntent pendingIntent7 = PendingIntent.getActivity(context, 0, intent7, PendingIntent.FLAG_IMMUTABLE);
                    views.setOnClickPendingIntent(R.id.button, pendingIntent7);
                    break;
                default:
                    break;
            }

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}

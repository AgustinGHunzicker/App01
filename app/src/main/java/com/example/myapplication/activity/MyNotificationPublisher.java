package com.example.myapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyNotificationPublisher extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String identificador = intent.getStringExtra("Identificador".toString());
        Log.i("broadcast", "Ingreso al escuchador");
    }

}

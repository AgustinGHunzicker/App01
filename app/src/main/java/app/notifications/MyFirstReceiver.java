package app.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import SendMeal.app.R;

public class MyFirstReceiver extends BroadcastReceiver {
    public static final String OFERTA = "On Sale";
    private Context contexto;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID=0;


    @Override
    public void onReceive(Context context, Intent intent) {
        contexto=context;
        String identificador = intent.getStringExtra("Identificador");
        createNotificationChannel();
        createNotification();


        Log.i("onReceive", "notificacion? ");
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//para chequear la version

            CharSequence name = "NOTIFICACION";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = contexto.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
            //registra la aplicacion en un canal
        }
    }

    private void createNotification(){
        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(contexto, "NOTIFICACION");
        myBuilder.setSmallIcon(R.drawable.ic_baseline_directions_bike_24);
        myBuilder.setContentTitle("Su pedido fue realizado");
        myBuilder.setContentText("El local confirmó su pedido y pronto llegará a su domicilio");
        myBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        myBuilder.setLights(Color.MAGENTA, 1000,1000);
        myBuilder.setVibrate(new long[]{1000,1000,1000});
        myBuilder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(contexto);
        notificationManagerCompat.notify(NOTIFICACION_ID,myBuilder.build());
    }
}

class MyNotificationPublisher extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String identificador = intent.getStringExtra("Identificador".toString());
        Log.i("broadcast", "Ingreso al escuchador");
    }

}

package app.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import app.activity.ActivityHome;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String s){

    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
      //  Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Pueden validar si el mensaje trae datos
        if (remoteMessage.getData().size() > 0) {
         //   Log.d(TAG, "Payload del mensaje: " + remoteMessage.getData());
            // Realizar alguna acción en consecuencia
        }
        // Pueden validar si el mensaje trae una notificación
        if (remoteMessage.getNotification() != null) {
        //    Log.d(TAG, "Cuerpo de la notificación del mensaje: " + remoteMessage.getNotification().getBody());
            // También pueden usar:
            // remoteMessage.getNotification().getTitle()
        }
        // Cualquier otra acción que quieran realizar al recibir un mensaje de firebase, la pueden realizar aca
        // EJ:
        sendNotification("Cuerpo de la notificacion");
    }

    // Función para crear una notificación (completar)
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, ActivityHome.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // PendingIntent pendingIntent = ....

        //NotificationCompat.Builder notificationBuilder =
        //        new NotificationCompat.Builder(this, channelId)
        //                          ....
        //               .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //    NotificationChannel channel = new NotificationChannel(....);
            //notificationManager.createNotificationChannel(channel);
        }

        //notificationManager.notify(0 /* ID notificación */, notificationBuilder.build());
    }

}
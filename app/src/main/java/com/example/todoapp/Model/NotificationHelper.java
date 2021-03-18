package com.example.todoapp.Model;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.todoapp.R;

import static com.example.todoapp.Utils.Constants.CHANNEL_ID;
import static com.example.todoapp.Utils.Constants.CHANNEL_NAME;

/**
 * Classe qui gère les notifications de l'appli avec les versions et autres informations
 */
public class NotificationHelper extends ContextWrapper {


    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {                                                      // paramètre pour les priorités
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle("Réveil")
                .setContentText("Tout est ok")
                .setSmallIcon(R.drawable.ic_alarm_24);
    }
}
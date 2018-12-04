package co.th.udrinkidrive.datalayer.gcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import androidx.core.app.NotificationCompat;
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final static int MAX_VOLUME = 100;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Map<String, String> data = remoteMessage.getData();

        Log.d("Tag","onMessageReceived");
//        sendNotification(notification, data);
//        sendNotificationData(notification, data);

    }

//    private void sendNotificationData(RemoteMessage.Notification notification, Map<String, String> data) {
//        if(data.get("key_1").equalsIgnoreCase("Value for key_1")){
//            try {
//                Uri notification_uri_sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);
//                Log.d("Tag","notification_uri : "+notification_uri_sound);
//                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification_uri_sound);
//                MediaPlayer thePlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
//
//                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                am.setStreamVolume(AudioManager.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
//                thePlayer.setVolume(MAX_VOLUME,MAX_VOLUME);
//                r.setVolume(MAX_VOLUME);
//                thePlayer.start();
//                r.play();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    private void sendNotificationShow(){

    }

//    /**
//     * Create and show a custom notification containing the received FCM message.
//     *
//     * @param notification FCM notification payload received.
//     * @param data FCM data payload received.
//     */
//    private void sendNotification(RemoteMessage.Notification notification, Map<String, String> data) {
//        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo_loading);
//
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        Uri notification_uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "MyFirebaseMessagingService")
////                .setContentTitle(notification.getTitle()+" : service")
//////                .setContentInfo(notification.getTitle())
//////                .setContentText(notification.getBody())
//                .setContentTitle(data.get("title"))
//                .setContentInfo(data.get("title"))
//                .setContentText(data.get("body"))
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent)
//                .setSound(notification_uri, AudioManager.STREAM_NOTIFICATION)
//                .setLargeIcon(icon)
//                .setColor(Color.BLACK)
//                .setVibrate(new long[]{2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500
//                        , 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 2500, 100})
//                .setLights(Color.BLACK, 1000, 300)
//                .setSmallIcon(R.drawable.logo_loading);
//
//        MediaPlayer thePlayerFreelance = MediaPlayer.create(this, R.raw.sound);
//        thePlayerFreelance.setVolume(MAX_VOLUME, MAX_VOLUME);
//
//
////        try {
////            Uri notification_uri_sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sound);
////            Log.d("Tag","notification_uri : "+notification_uri_sound);
////            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification_uri_sound);
////            r.play();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        try {
//            String picture_url = data.get("picture_url");
//            if (picture_url != null && !"".equals(picture_url)) {
//                URL url = new URL(picture_url);
//                Bitmap bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                notificationBuilder.setStyle(
//                        new NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setSummaryText(notification.getBody())
//                );
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Notification Channel is required for Android O and above
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    "MyFirebaseMessagingService", "channel_name", NotificationManager.IMPORTANCE_HIGH
//            );
//
//
//            AudioAttributes att = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
//                    .build();
//            channel.setSound(notification_uri,att);
//            channel.setDescription("channel description");
//            channel.setShowBadge(true);
//            channel.canShowBadge();
//            channel.enableLights(true);
//            channel.setLightColor(Color.RED);
//            channel.enableVibration(true);
//            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(1, notificationBuilder.build());
//    }
}
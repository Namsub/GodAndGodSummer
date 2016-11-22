package com.juntcompany.godandgodsummer.Main;

/**
 * Created by z on 2016-11-17.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.RemoteMessage;
import com.juntcompany.godandgodsummer.Chatting.ChattingActivity;
import com.juntcompany.godandgodsummer.Data.Chat;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //추가한것
        sendNotification(remoteMessage.getData().get("data1"), remoteMessage.getData().get("data2").split("[.]")[0]);
    }

    private void sendNotification(String room_name, String id) {
        String room_list = PropertyManager.getInstance().getRoomList();
        //방개설

        //chat.friendName = "사람";
        String lastSpeak = "사람이 말한 마지막 말";
        String lastTime = "시간";
        //number_of_persons = 1;
        if(room_list.length() == 0)
            room_list += room_name + "/" + lastSpeak + "/" + lastTime;
        else
            room_list += "|" + room_name + "/" + lastSpeak + "/" + lastTime;

        Log.i("FCMService", "room_list : " + room_list);
        Log.i("FCMService", "room_name : " + room_name);
        Log.i("FCMService", "id : " + id);

        PropertyManager.getInstance().setRoomList(room_list);
        FirebaseDatabase.getInstance().getReference().child(room_name).child(id).setValue(id);
        
        Intent intent = new Intent(this, ChattingActivity.class);
        intent.putExtra("room_name", room_name);
        intent.putExtra("email", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("FCM Push Test")
                .setContentText(room_name + "/" + id)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
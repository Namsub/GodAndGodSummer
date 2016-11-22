package com.juntcompany.godandgodsummer.Chatting;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by z on 2016-11-16.
 */

public class FirebaseMessagingService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}

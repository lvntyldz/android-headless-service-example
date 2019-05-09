package com.example.android.headless.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class DemoService extends Service {

    MediaPlayer player;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started - onStartCommand", Toast.LENGTH_SHORT).show();
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();

        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, com.example.android.headless.service.MainActivity.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped - onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

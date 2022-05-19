package com.learning.musicplayer2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.learning.musicplayer2".equals(intent.getAction())) {
            String alert = intent.getStringExtra("com.learning.musicplayer2");
            Toast.makeText(context, alert, Toast.LENGTH_SHORT).show();
        }
    }

}

package com.learning.musicplayer2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("music stops".equals(intent.getAction())) {
            String receivedText = intent.getStringExtra("music goes on");
            Toast.makeText(context, receivedText, Toast.LENGTH_SHORT).show();
        }
    }

}

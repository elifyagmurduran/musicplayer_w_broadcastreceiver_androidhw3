package com.learning.broadcastsender;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
    }

    public void sendBroadcast(View v) {
        Intent intent1 = new Intent("com.codinginflow.EXAMPLE_ACTION");
        intent1.putExtra("com.codinginflow.EXTRA_TEXT", "Broadcast received");
        sendBroadcast(intent1);
    }

    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context1, Intent intent1) {
            String alert1 = intent1.getStringExtra("com.codinginflow.EXTRA_TEXT");
            textView.setText(alert1);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter1 = new IntentFilter("com.codinginflow.EXAMPLE_ACTION");
        registerReceiver(myBroadcastReceiver, filter1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }
}
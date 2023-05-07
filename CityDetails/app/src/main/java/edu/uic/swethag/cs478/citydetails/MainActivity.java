package edu.uic.swethag.cs478.citydetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends MenuActivity {
    private String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter("edu.uic.swethag.cs478.citychooser");
        BroadcastReceiver objReceiver = new MyBroadcastReceiver();
        registerReceiver(objReceiver, intentFilter, "edu.uic.cs478.spring23.mp3", null);
    }
}
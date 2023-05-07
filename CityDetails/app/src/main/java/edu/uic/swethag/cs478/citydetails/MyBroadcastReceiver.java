package edu.uic.swethag.cs478.citydetails;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // extract any extra data sent with the broadcast
        String activityType = intent.getStringExtra("city");

        if (activityType != null) {
            if (activityType.equals("New_York_City")) {
                Intent i = new Intent(context, NYCActivity.class);
                context.startActivity(i);
            } else if (activityType.equals("Orlando")) {
                Intent i = new Intent(context, OrlandoActivity.class);
                context.startActivity(i);
            } else {
                Log.e("City details", "Invalid activity type: " + activityType);
            }
        }
    }
}

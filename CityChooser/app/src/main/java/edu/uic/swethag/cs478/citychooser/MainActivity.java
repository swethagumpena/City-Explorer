package edu.uic.swethag.cs478.citychooser;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    protected Button newyorkButton;
    protected Button orlandoButton;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the main layout (from the res folder)
        setContentView(R.layout.activity_main);

        // Bind the interface elements to the corresponding fields
        newyorkButton = findViewById(R.id.newyork);
        orlandoButton = findViewById(R.id.orlando);

        intent.setAction("edu.uic.swethag.cs478.citychooser");

        newyorkButton.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "New York city was selected", Toast.LENGTH_SHORT).show();
            intent.putExtra("city", "New_York_City");
            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            checkPermissions();
        });

        orlandoButton.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Orlando was selected", Toast.LENGTH_SHORT).show();
            intent.putExtra("city", "Orlando");
            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            checkPermissions();
        });
    }

    public void checkPermissions() {
        // Check if the permission is already granted
        int permissionCheck = ContextCompat.checkSelfPermission(this, "edu.uic.cs478.spring23.mp3");
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{"edu.uic.cs478.spring23.mp3"}, 1);
        } else {
            // Permission is already granted, broadcast the intent
            sendBroadcast(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, broadcast the intent
                sendBroadcast(intent);
            } else {
                // Permission is not granted, show an error message
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
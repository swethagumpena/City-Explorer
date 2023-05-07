package edu.uic.swethag.cs478.citydetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().setTitle("Top Tourist Attractions");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String activityName = "";
        int id = item.getItemId();
        Intent intent = new Intent();

        switch (id) {
            case R.id.action_NewYork:
                activityName = getClass().getName();
                if (!activityName.equals("edu.uic.swethag.cs478.citydetails.NYCActivity")) {
                    intent.setClass(this, NYCActivity.class);
                    startActivity(intent);
                }
                return true;
            case R.id.action_Orlando:
                activityName = getClass().getName();
                if (!activityName.equals("edu.uic.swethag.cs478.citydetails.OrlandoActivity")) {
                    intent.setClass(this, OrlandoActivity.class);
                    startActivity(intent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
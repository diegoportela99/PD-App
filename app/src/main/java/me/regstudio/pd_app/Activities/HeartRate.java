package me.regstudio.pd_app.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.regstudio.pd_app.R;

/**
 * Created by diego on 8/3/2018.
 */

public class HeartRate extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_heart_rate);

    }
    public void app1(View view){

        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=net.kibotu.heartrateometer.app"));
        startActivity(appIntent);
    }
}

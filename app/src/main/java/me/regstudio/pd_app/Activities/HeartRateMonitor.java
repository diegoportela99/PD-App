package me.regstudio.pd_app.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;
import me.regstudio.pd_app.R;


/**
 * This class extends Activity to handle a picture preview, process the preview
 * for a red values and determine a heart beat.
 *
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class HeartRateMonitor extends AppCompatActivity {
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

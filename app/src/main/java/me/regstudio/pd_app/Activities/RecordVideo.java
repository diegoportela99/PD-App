package me.regstudio.pd_app.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import me.regstudio.pd_app.R;

/**
 * Created by diego on 8/3/2018.
 */

public class RecordVideo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_record_video);

        Toast.makeText(RecordVideo.this,
                "File Selected: " + MainActivity.fileName, Toast.LENGTH_LONG).show();
        Toast.makeText(RecordVideo.this,
                "Doc Selected: " + MainActivity.DoctorSelectedID, Toast.LENGTH_LONG).show();
    }
}

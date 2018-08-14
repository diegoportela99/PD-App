package me.regstudio.pd_app.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.regstudio.pd_app.R;

/**
 * Created by diego on 8/14/2018.
 */

public class DoctorList extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);
        setTitle("Doctor List");


    }
}

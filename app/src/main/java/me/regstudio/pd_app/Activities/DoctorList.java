package me.regstudio.pd_app.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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

    //Adds an item to the list
    private void AddDoctor() {

    }


    //method called after a doctor is selected
    private void setDoc(String x) {
        MainActivity.DoctorSelectedID = x;
    }

}

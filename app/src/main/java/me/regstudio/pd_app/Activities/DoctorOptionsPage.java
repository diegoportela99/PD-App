package me.regstudio.pd_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.R;

public class DoctorOptionsPage extends AppCompatActivity {

    @BindView(R.id.view_patients)
    Button viewPatients;
    @BindView(R.id.my_details)
    Button myDetails;
    @BindView(R.id.map)
    Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_options_page);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.view_patients, R.id.my_details, R.id.map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_patients:
                break;
            case R.id.my_details:
                Intent myDetail = new Intent(this, MyDetails.class);
                startActivity(myDetail);
                break;
            case R.id.map:
                break;
        }
    }
}

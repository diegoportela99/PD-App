package me.regstudio.pd_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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
    Button logOut;

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
                Intent intent = new Intent(this, SelectDoctor.class);
                startActivity(intent);
                break;
            case R.id.my_details:
                Intent intent3 = new Intent(this, MyDetails.class);
                startActivity(intent3);
                break;
            case R.id.map:
                FirebaseAuth.getInstance().signOut();
                Intent logOut = new Intent(this, DoctorLogin.class);
                startActivityForResult(logOut, 1);
                Toast.makeText(this, "User Logged Out!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

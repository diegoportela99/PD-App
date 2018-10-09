package me.regstudio.pd_app.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.R;

public class MyDetails extends AppCompatActivity {

    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.first_name)
    TextView firstName;
    @BindView(R.id.last_name)
    TextView lastName;
    @BindView(R.id.ok_db)
    Button okDb;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);
        ButterKnife.bind(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
    }

    @OnClick(R.id.ok_db)
    public void onViewClicked() {
    }
}

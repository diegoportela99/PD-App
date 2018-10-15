package me.regstudio.pd_app.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.R;

public class MyDetails extends AppCompatActivity {

    @BindViews({
            R.id.edit_doctor_first_name,
            R.id.edit_doctor_last_name,
            R.id.edit_doctor_specialty
    }) List<EditText> editTexts;

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

    // ButterKnife Action that disables EditTexts' input.
    static final ButterKnife.Action<EditText> DISABLE_EDIT = new ButterKnife.Action<EditText>() {
        @Override
        public void apply(@NonNull EditText view, int index) {
            view.setInputType(InputType.TYPE_NULL);
        }
    };

    // ButterKnife Action that enables EditTexts' input using preset input types.
    static final ButterKnife.Action<EditText> ENABLE_EDIT = new ButterKnife.Action<EditText>() {
        @Override
        public void apply(@NonNull EditText view, int index) {
            view.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);
        ButterKnife.bind(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
    }

    private void saveDoctorInfo() {
    }

    @OnClick(R.id.ok_db)
    public void onViewClicked() {
        if (okDb.getText().equals("Edit")) {
            //Enable all EditTexts and change the button name
            ButterKnife.apply(editTexts, ENABLE_EDIT);
            okDb.setText("Save");
        } else {
            //Disable all EditTexts and change the button name
            ButterKnife.apply(editTexts, DISABLE_EDIT);
            okDb.setText("Edit");
            // Save to firebase
            saveDoctorInfo();
        }
    }

}

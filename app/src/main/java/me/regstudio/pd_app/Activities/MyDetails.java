package me.regstudio.pd_app.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.DataStructures.DoctorFile;
import me.regstudio.pd_app.R;

import static java.security.AccessController.getContext;

public class MyDetails extends AppCompatActivity {

    private boolean editable;

    @BindViews({
            R.id.edit_doctor_first_name,
            R.id.edit_doctor_last_name,
            R.id.edit_doctor_specialty
    }) List<EditText> editTexts;

    // For individual edits
    @BindView(R.id.edit_doctor_first_name) EditText doctorFirstName;
    @BindView(R.id.edit_doctor_last_name) EditText doctorLastName;
    @BindView(R.id.edit_doctor_specialty) EditText doctorSpecialty;

    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.first_name)
    TextView firstName;
    @BindView(R.id.last_name)
    TextView lastName;
    @BindView(R.id.ok_db)
    Button okDb;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;

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
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        editable = false;
        ButterKnife.apply(editTexts, DISABLE_EDIT);
        // Load from firebase
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = database.getReference().child("doctors").child(currentUser.getUid());
        // Only pull data from firebase if the user is logged in
        if (currentUser != null) {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Retrieve data from firebase. If null, set to empty.
                    try {
                        doctorFirstName.setText(dataSnapshot.child("firstName").getValue().toString());
                        doctorLastName.setText(dataSnapshot.child("lastName").getValue().toString());
                        doctorSpecialty.setText(dataSnapshot.child("specialty").getValue().toString());
                    } catch (NullPointerException e) {;}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }

    private void saveDoctorInfo() {
        /* Saves doctor information into firebase database */
        // Get current user and firebase db ref.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = database.getReference();

        //Only proceed if the logged in user is valid
        if (currentUser != null) {

            // Create new doctorFile object and populate it.
            DoctorFile doctorFile = new DoctorFile();

            doctorFile.addUserId(currentUser.getUid());
            doctorFile.addFirstName(editTexts.get(0).getText().toString());
            doctorFile.addLastName(editTexts.get(1).getText().toString());
            doctorFile.addSpecialty(editTexts.get(2).getText().toString());

            // Store new doctorFile into firebase database child "doctors"
            // Each doctor is indexed by their firebaseAuth user ID.
            databaseReference.child("doctors").child(currentUser.getUid()).setValue(doctorFile);

            Toast.makeText(this, "Successfully saved your information!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ok_db)
    public void onViewClicked() {
        if (!editable) {
            //Enable all EditTexts and change the button name
            ButterKnife.apply(editTexts, ENABLE_EDIT);
            okDb.setText("Save");
        } else {
            // Check if the fields are not empty.
            boolean saveable = true;
            for (EditText editText : editTexts) {
                if (editText.getText().toString().equals(""))
                    saveable = false;
            }
            // Save to firebase if all fields are filled out
            if (saveable) {
                saveDoctorInfo();
                //Disable all EditTexts and change the button name
                ButterKnife.apply(editTexts, DISABLE_EDIT);
                okDb.setText("Edit");
            } else {
                Toast.makeText(this, "Please fill out all fields to save.", Toast.LENGTH_LONG).show();
            }
        }
        // Swap the button state.
        editable = !editable;
    }

}

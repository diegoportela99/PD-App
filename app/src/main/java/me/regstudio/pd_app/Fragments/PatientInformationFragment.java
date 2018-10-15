package me.regstudio.pd_app.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.DataStructures.PatientFile;
import me.regstudio.pd_app.R;

/**
 * Class: PatientInformationFragment
 * Extends: {@link Fragment}
 * Author: Carlos Tirado < Carlos.TiradoCorts@uts.edu.au> and YOU!
 * Description:
 * <p>
 * This fragment's job will be that to display patients information, and be able to edit that
 * information (either edit it in this fragment or a new fragment, up to you!)
 * <p>

 */
public class PatientInformationFragment extends Fragment {


    // Note how Butter Knife also works on Fragments, but here it is a little different
    //@BindView(R.id.frameLayout2)
    //TextView blankFragmentTV;

    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;

    private boolean editable;

    @BindView(R.id.edit_button) Button editButton;

    // For individual editing.
    @BindView(R.id.edit_first_name) EditText firstName;
    @BindView(R.id.edit_last_name) EditText lastName;
    @BindView(R.id.edit_sex) EditText sex;
    @BindView(R.id.edit_age) EditText age;
    @BindView(R.id.edit_height) EditText height;
    @BindView(R.id.edit_weight) EditText weight;
    @BindView(R.id.edit_condition) EditText condition;

    // For group editing.
    @BindViews({
            R.id.edit_first_name,
            R.id.edit_last_name,
            R.id.edit_sex,
            R.id.edit_age,
            R.id.edit_height,
            R.id.edit_weight,
            R.id.edit_condition
    }) List<EditText> editTexts;

    // Array used to store the InputTypes of the corresponding EditTexts.
    static int[] editTextTypes = {
            InputType.TYPE_CLASS_TEXT, // First Name
            InputType.TYPE_CLASS_TEXT, // Last Name
            InputType.TYPE_CLASS_TEXT, // Sex
            InputType.TYPE_CLASS_NUMBER, // Age
            InputType.TYPE_CLASS_NUMBER, // Height
            InputType.TYPE_CLASS_NUMBER, // Weight
            InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE, // Condition
    };

    public PatientInformationFragment() {
        // Required empty public constructor
    }

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
            view.setInputType(editTextTypes[index]);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: Instead of hardcoding the title perhaps take the user name from somewhere?
        // Note the use of getActivity() to reference the Activity holding this fragment
        getActivity().setTitle("Username Information");
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_information, container, false);

        // Note how we are telling butter knife to bind during the on create view method
        ButterKnife.bind(this, v);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Now that the view has been created, we can use butter knife functionality
        //blankFragmentTV.setText("Welcome to this fragment");
        // Disable all editTexts on initialisation.
        ButterKnife.apply(editTexts, DISABLE_EDIT);
        editable = false;
        // TODO: Load data from firebase
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = database.getReference().child("patients").child(currentUser.getUid());
        // Only attempt to pull data if the user is actually logged in
        if (currentUser != null) {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Retrieve data from Firebase. If null, set to empty.
                    //PatientFile patientFile = dataSnapshot.getValue(PatientFile.class);
                    try {
                        firstName.setText(dataSnapshot.child("firstName").getValue().toString());
                        lastName.setText(dataSnapshot.child("lastName").getValue().toString());
                        sex.setText(dataSnapshot.child("sex").getValue().toString());
                        age.setText(dataSnapshot.child("age").getValue().toString());
                        height.setText(dataSnapshot.child("height").getValue().toString());
                        weight.setText(dataSnapshot.child("weight").getValue().toString());
                        condition.setText(dataSnapshot.child("condition").getValue().toString());
                    } catch (NullPointerException e) {;}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }

    private void savePatientInfo() {
        /* Used to save information to the Firebase database. */
        // Get current user and Firebase database reference.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference = database.getReference();

        // Only proceed if the logged in user is valid.
        if (currentUser != null) {

            // Create new patientFile object and populate it.
            PatientFile patientFile = new PatientFile();

            patientFile.addUserId(currentUser.getUid());
            patientFile.addFirstName(editTexts.get(0).getText().toString());
            patientFile.addLastName(editTexts.get(1).getText().toString());
            patientFile.addSex(editTexts.get(2).getText().toString());
            patientFile.addAge(Integer.parseInt(editTexts.get(3).getText().toString()));
            patientFile.addHeight(Integer.parseInt(editTexts.get(4).getText().toString()));
            patientFile.addWeight(Integer.parseInt(editTexts.get(5).getText().toString()));
            patientFile.addCondition(editTexts.get(6).getText().toString());

            // Store new patientFile into Firebase database child "patients".
            // Each patient is indexed by their FirebaseAuth user ID.
            databaseReference.child("patients").child(currentUser.getUid()).setValue(patientFile);

            Toast.makeText(getContext(), "Successfully saved your information!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.edit_button)
    public void editInfo(View view) {
        /* Used to edit and save patient information */
        // TODO: On startup, button requires two taps before this method seems to trigger. Needs to be fixed.
        // Enable/Disable all EditTexts based on current state of the button.
        if (!editable) {
            // Enable all EditTexts and change the button name
            ButterKnife.apply(editTexts, ENABLE_EDIT);
            editButton.setText("Save");
        } else {
            // Check if the fields are not empty.
            boolean saveable = true;
            for (EditText editText : editTexts) {
                if (editText.getText().toString().equals(""))
                    saveable = false;
            }
            // Save data to Firebase if fields are filled out.
            if (saveable) {
                savePatientInfo();
                // Disable all EditTexts and change the button name
                ButterKnife.apply(editTexts, DISABLE_EDIT);
                editButton.setText("Edit");
            } else {
                Toast.makeText(getContext(), "Please fill out all fields to save.", Toast.LENGTH_LONG).show();
            }
        }
        // Swap the button state.
        editable = !editable;
    }

}

package me.regstudio.pd_app.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import me.regstudio.pd_app.R;

/**
 * Created by diego on 8/14/2018.
 */

public class DoctorList extends AppCompatActivity/* implements AdapterView.OnItemSelectedListener*/ {

    @BindView(R.id.spinner) Spinner doctorSpinner;
    private List<String> doctorList = new ArrayList<>();

    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);
        setTitle("Doctor List");
        // Setup ButterKnife
        ButterKnife.bind(this);

        /*
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.doctors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        */

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, doctorList);

        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        final DatabaseReference doctorReference = database.getReference().child("patients");
        doctorReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve a list of doctors from Firebase
                for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                    String fullName = doctorSnapshot.child("firstName").getValue().toString() + " " + doctorSnapshot.child("lastName").getValue().toString();
                    doctorList.add(fullName);
                }
                Spinner doctorSpinner = findViewById(R.id.spinner);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorSpinner.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}});
    }
    /*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();

        setDoc(text);
    }
    */

    @OnItemSelected(R.id.spinner)
    public void itemSelected(Spinner spinner, int position) {
        Toast.makeText(this, doctorList.get(position).toString(),Toast.LENGTH_SHORT);
    }

    /*
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }
    */

    //method called after a doctor is selected
    private void setDoc(String x) {
        MainActivity.DoctorSelectedID = x;
    }
}

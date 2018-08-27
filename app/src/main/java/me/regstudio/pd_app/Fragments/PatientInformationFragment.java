package me.regstudio.pd_app.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @BindView(R.id.edit_button) Button editButton;

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
    }

    @OnClick(R.id.edit_button)
    public void editInfo(View view) {
        /* Used to edit and save patient information */
        // TODO: On startup, button requires two taps before this method seems to trigger. Needs to be fixed.
        // Enable/Disable all EditTexts based on current state of the button.
        if (editButton.getText() == "Edit") {
            // Enable all EditTexts and change the button name
            ButterKnife.apply(editTexts, ENABLE_EDIT);
            editButton.setText("Save");
        } else {
            // Disable all EditTexts and change the button name
            ButterKnife.apply(editTexts, DISABLE_EDIT);
            editButton.setText("Edit");
        }
    }

}

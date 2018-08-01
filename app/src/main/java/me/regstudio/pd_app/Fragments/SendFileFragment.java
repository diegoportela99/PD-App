package me.regstudio.pd_app.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import me.regstudio.pd_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendFileFragment extends Fragment {

    protected Button sendFile;


    public SendFileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_send_file, container, false);


//        sendFile = (Button) view.findViewById(R.id.send_file_B1);
//        //sendFile.setOnClickListener(this);
//
//        sendFile.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                // click handling code
//                Toast.makeText(getActivity(), "test button",
//                        Toast.LENGTH_LONG).show();
//            }
//        });

        // Inflate the layout for this fragment

        return view;
    }

}

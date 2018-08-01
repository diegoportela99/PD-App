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
public class DataPacketFragment extends Fragment {


    public DataPacketFragment() {
        // Required empty public constructor
    }

    Button sendFile, writeMessage, sendData, heartRate, selectDoc, recVid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_packet, container, false);


        sendFile = (Button) view.findViewById(R.id.SendFileB);
        recVid = (Button) view.findViewById(R.id.RecB);
        sendData = (Button) view.findViewById(R.id.SendDataB);
        selectDoc = (Button) view.findViewById(R.id.SelectB);
        heartRate = (Button) view.findViewById(R.id.HeartB);
        writeMessage = (Button) view.findViewById(R.id.WriteMessageB);


        sendFile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();
            }
        });

        recVid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();
            }
        });

        sendData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();
            }
        });

        selectDoc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();
            }
        });

        heartRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();
            }
        });

        writeMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}

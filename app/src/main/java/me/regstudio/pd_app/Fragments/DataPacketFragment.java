package me.regstudio.pd_app.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import me.regstudio.pd_app.Activities.DoctorList;
import me.regstudio.pd_app.Activities.HeartRate;
import me.regstudio.pd_app.Activities.MainActivity;
import me.regstudio.pd_app.Activities.RecordVideo;
import me.regstudio.pd_app.Activities.SelectDoctor;
import me.regstudio.pd_app.Activities.SendFile;
import me.regstudio.pd_app.Activities.WriteMessage;
import me.regstudio.pd_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataPacketFragment extends Fragment {


    private MainActivity obj;

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

//        if (MainActivity.DoctorSelectedID != null) {
//
//        } else {
//            sendFile.setEnabled(false);
//            recVid.setEnabled(false);
//            sendData.setEnabled(false);
//            selectDoc.setEnabled(true);
//            heartRate.setEnabled(false);
//            writeMessage.setEnabled(false);
//        }



        sendFile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "Pleaae select your preference.",
                        Toast.LENGTH_LONG).show();

                sendFile();
            }
        });

        recVid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();

                recordVideo();
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

                selectDoctor();
            }
        });

        heartRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();

                heartRate();
            }
        });

        writeMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();

                writeMessage();
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result=data.getStringExtra("result");


            }
        }

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                String result=data.getStringExtra("result");


            }
        }

        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                String result=data.getStringExtra("result");


            }
        }

        if (requestCode == 4) {
            if (resultCode == Activity.RESULT_OK) {
                String result=data.getStringExtra("result");


            }

            if (resultCode == Activity.RESULT_CANCELED) {
                String result=data.getStringExtra("result");

                Doctorlist();
            }
        }

        if (requestCode == 5) {
            if (resultCode == Activity.RESULT_OK) {
                String result=data.getStringExtra("result");


            }
        }

        if (requestCode == 6) {
            if (requestCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");


            }
        }
    }

    public void Doctorlist() {
        Intent SendFile = new Intent(getActivity(), DoctorList.class);
        startActivityForResult(SendFile, 6);
    }

    public void sendFile() {
        Intent SendFile = new Intent(getActivity(), SendFile.class);
        startActivityForResult(SendFile, 1);
    }

    public void recordVideo() {
        Intent RecordVideo = new Intent(getActivity(), RecordVideo.class);
        startActivityForResult(RecordVideo, 2);
    }

    public void heartRate() {
        Intent HeartRate = new Intent(getActivity(), HeartRate.class);
        startActivityForResult(HeartRate, 3);
    }

    public void selectDoctor() {
        Intent SelectDoctor = new Intent(getActivity(), SelectDoctor.class);
        startActivityForResult(SelectDoctor, 4);
    }

    public void writeMessage() {
        Intent WriteMessage = new Intent(getActivity(), WriteMessage.class);
        startActivityForResult(WriteMessage, 5);
    }

}

package me.regstudio.pd_app.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View.OnClickListener;

import me.regstudio.pd_app.Activities.DoctorList;
import me.regstudio.pd_app.Activities.Email;
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
    private String sendEmail_;

    public DataPacketFragment() {
        // Required empty public constructor
    }

    ImageButton sendFile, writeMessage, sendInfo, heartRate, selectDoc, recVid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_packet, container, false);


        sendFile = (ImageButton) view.findViewById(R.id.SendFileB);
        recVid = (ImageButton) view.findViewById(R.id.RecB);
        selectDoc = (ImageButton) view.findViewById(R.id.SelectB);
        heartRate = (ImageButton) view.findViewById(R.id.HeartB);
        writeMessage = (ImageButton) view.findViewById(R.id.WriteMessageB);
        sendInfo = (ImageButton) view.findViewById(R.id.SendInfoB);

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



        sendFile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "Please select your preference.",
                        Toast.LENGTH_LONG).show();

                sendFile();
            }
        });

        recVid.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();

                recordVideo();
            }
        });

        selectDoc.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();

                selectDoctor();
            }
        });

        heartRate.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();

                heartRate();
            }
        });

        writeMessage.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();

                writeMessage();
            }
        });

        sendInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // click handling code
                Toast.makeText(getActivity(), "test button",
                        Toast.LENGTH_LONG).show();



                //ADD THE EMAILS OF THE DOCTORS HERE>

                if (MainActivity.DoctorSelectedID != null && !MainActivity.DoctorSelectedID.isEmpty()) {
                    switch (MainActivity.DoctorSelectedID) {
                        case "Diego":
                            sendEmail_ = "diegoportela23@hotmail.com";
                            break;

                        case "Slesha":
                            sendEmail_ = "diegofportela1@gmail.com";
                            break;

                        case "Nathan":

                            break;

                        case "Tanmay":

                            break;

                        case "Zain":

                            break;

                        case "Mohammed":

                            break;
                    }

                    MainActivity.mail = sendEmail_;
                }

                sendEmail();
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
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");


            }
        }

        if (requestCode == 7) {
            if (resultCode == Activity.RESULT_OK) {
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

    public void sendEmail() {
        Intent sendmail = new Intent(getActivity(), Email.class);
        startActivityForResult(sendmail, 7);
    }

}

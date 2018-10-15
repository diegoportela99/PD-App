package me.regstudio.pd_app.Activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;

import me.regstudio.pd_app.R;


public class Email extends AppCompatActivity {

    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;

    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_email);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        mEditTextTo = findViewById(R.id.edit_text_to);
        mEditTextSubject = findViewById(R.id.edit_text_subject);
        mEditTextMessage = findViewById(R.id.edit_text_message);

        if (MainActivity.mail != null && !MainActivity.mail.isEmpty()) {
            mEditTextTo.setText(MainActivity.mail, TextView.BufferType.EDITABLE);
        }

        if (MainActivity.writing != null && !MainActivity.writing.isEmpty()) {
            mEditTextMessage.setText(MainActivity.writing, TextView.BufferType.EDITABLE);
        }

        Button check = findViewById(R.id.CheckB);
        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(Email.this, "Attached File: " + MainActivity.fileName,
                        Toast.LENGTH_LONG).show();
            }
        });

        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sendMail();
            }
        });
    }

    private void sendMail(){
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");
        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        // the attachment (MainActivity.fileName is the 'path')
        if (MainActivity.fileName != null && !MainActivity.fileName.isEmpty()) {
            intent.putExtra(Intent.EXTRA_STREAM, MainActivity.fileName);
        }



        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

}

package me.regstudio.pd_app.Activities;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ipaulpro.afilechooser.utils.FileUtils;

import me.regstudio.pd_app.R;

/**
 * Created by diego on 8/3/2018.
 */

public class SendFile extends AppCompatActivity{
    private static final String TAG = "FileChooserExampleActivity";

    private static final int REQUEST_CODE = 6384; // onActivityResult request
    // code
    Button storageBtn, uploadBtn;
    TextView fileNameTv;
    private StorageReference mStorageRef;
    private static final int GALLERY_INTENT = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        setContentView(R.layout.fragment_send_file);
        storageBtn = (Button) findViewById(R.id.storageBtn);
        fileNameTv = (TextView) findViewById(R.id.fileNameTv);
        uploadBtn = (Button) findViewById(R.id.uploadBtn);
        uploadBtn.setEnabled(false);
        storageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooser();
            }
        });

        uploadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SendFile.this, "File Successfully Uploaded!", Toast.LENGTH_SHORT).show();
            }
        });

        fileNameTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textViewFileName = fileNameTv.getText().toString().trim();
                uploadBtn.setEnabled(!textViewFileName.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        setContentView(storageBtn);
    }
    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, getString(R.string.chooser_title));
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        Log.i(TAG, "Uri = " + uri.toString());
                        try {
                            // Get the file path from the URI
                            final String path = FileUtils.getPath(this, uri);
                            Toast.makeText(SendFile.this,
                                    "File Selected: " + path, Toast.LENGTH_LONG).show();
                            fileNameTv.setText("Selected file: " + path);

                            MainActivity.fileName = path;
                            Toast.makeText(this, "File Selected!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e("FileSelectorTestActivity", "File select error", e);
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

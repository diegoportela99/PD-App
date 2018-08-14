package me.regstudio.pd_app.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.OnClick;
import me.regstudio.pd_app.R;

/**
 * Created by diego on 8/3/2018.
 */

//Create a list of clickable buttons that link to a doctor

public class SelectDoctor extends AppCompatActivity {

    Button DocButton;
    MainActivity obj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_doc);


        DocButton = (Button) findViewById(R.id.ChangeDocB);


        DocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    protected void selectDoc(String doc) {
        obj.selectDoctor(doc);
    }
}

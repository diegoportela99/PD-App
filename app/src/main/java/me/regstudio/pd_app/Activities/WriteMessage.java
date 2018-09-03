package me.regstudio.pd_app.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.R;
/**
 * Created by diego on 8/3/2018.
 */

public class WriteMessage extends AppCompatActivity {

    @BindView(R.id.message_edit_text) EditText messageEditText;
    @BindView(R.id.char_count_text) TextView charCountText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_message);
        // Setup ButterKnife
        ButterKnife.bind(this);
    }

    private void writeMessageToPacket(String message) {
        // TODO: Actual writing to data packet occurs here.
    }

    @OnClick(R.id.add_message_button)
    public void addMessage() {
        // Add the message to the data packet
        try {
            // Get text from the message EditText.
            String message = String.valueOf(messageEditText.getText());

            /* TODO: Write message to data packet (writeMessageToPacket function).
            *  Implementation depends on the form of data packet used and method of writing data.
            *  May need things more than just the text of the EditText above depending on this.
            *  writeMessageToPacket function may change if so.
            */
            writeMessageToPacket(message);

            Toast.makeText(getApplicationContext(), "Successfully added message!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            // Replace above exception with a specific one after implementation of message write.

            /* TODO: Implement error handling specific to data write implementation.
            *  Try-Catch block may not be required depending on the method of data writing and data packet chosen.
            */

            Toast.makeText(getApplicationContext(), "Failed to add message.", Toast.LENGTH_LONG).show();
        }

    }

}

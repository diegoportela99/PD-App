package me.regstudio.pd_app.Activities;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.Exceptions.InvalidMessageException;
import me.regstudio.pd_app.R;
/**
 * Created by diego on 8/3/2018.
 */

public class WriteMessage extends AppCompatActivity {

    @BindView(R.id.message_edit_text) EditText messageEditText;
    @BindView(R.id.char_count_text) TextView charCountText;

    private final TextWatcher messageTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Get the number of characters in the message EditText.
            int length = s.length();
            // Set the char count TextView to "Length: x character(s).
            String displayText = String.format(Locale.ENGLISH, "Length: %d %s", s.length(), length == 1 ? "character" : "characters");
            charCountText.setText(displayText);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_message);
        // Setup ButterKnife
        ButterKnife.bind(this);
        // Setup char count watcher.
        messageEditText.addTextChangedListener(messageTextWatcher);
    }

    private void writeMessageToPacket(String message) throws InvalidMessageException {
        // Throw InvalidMessageException if message is empty.
        if (message.equals("")) {
            throw new InvalidMessageException("Invalid message: Cannot add an empty message.", 1);
        }
        // TODO: Actual writing to data packet occurs here.

        MainActivity.writing = message;
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

        } catch (InvalidMessageException e) {

            /* TODO: Implement error handling specific to data write implementation.
            *  InvalidMessageException can be written to be flexible with errors involving writing the message to the data packet.
            */
            if (e.getCode() == e.EMPTY_MESSAGE) {
                Toast.makeText(getApplicationContext(), "Failed to add message: can't add an empty message.", Toast.LENGTH_LONG).show();
            } else if (e.getCode() == e.GENERAL_FAILURE) {
                Toast.makeText(getApplicationContext(), "Failed to add message.", Toast.LENGTH_LONG).show();
            }
        }

    }

}

package me.regstudio.pd_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.R;

public class DoctorLogin extends AppCompatActivity {

    @BindView(R.id.doctor_email)
    EditText doctorEmail;
    @BindView(R.id.doctor_password)
    EditText doctorPassword;
    @BindView(R.id.doctor_login)
    Button doctorLogin;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.doctor_register)
    TextView doctorRegister;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }


    private boolean userLogin() {
        String username = doctorEmail.getText().toString();
        String password = doctorPassword.getText().toString();

        if (username == null && password == null) {
            Toast.makeText(this, "Fields are empty. Login Unsuccessful", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (username == null) {
            Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
            // registerEmail.setError("Email is required");
            //registerEmail.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {

            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            //registerEmail.setError("Please enter a valid email");
            //registerRePassword.requestFocus();
            return false;
        }

        if (password == null) {

            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            //registerPassword.setError("Password is required");
            //registerPassword.requestFocus();
            return false;
        }


        if (password.length() < 6) {
            Toast.makeText(this, "Please enter a password more than 6 words", Toast.LENGTH_SHORT).show();
            return false;
        }

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return true;
    }


    @OnClick(R.id.doctor_login)
    public void onViewClicked() {

        if (userLogin() == true) {
            Intent intent = new Intent(this, DoctorOptionsPage.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }
}

package me.regstudio.pd_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.R;


public class RegisterUser extends AppCompatActivity {
    @BindView(R.id.register_email)
    EditText registerEmail;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_rePassword)
    EditText registerRePassword;
    @BindView(R.id.register_user)
    Button register_user;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();


    }



    private boolean registerUser() {
        String email = registerEmail.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String rePassword = registerRePassword.getText().toString().trim();
        if (email == null) {
            Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
            // registerEmail.setError("Email is required");
            //registerEmail.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

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
        if (rePassword == null) {

            Toast.makeText(this, "Please re-enter your password", Toast.LENGTH_SHORT).show();
            //registerPassword.setError("Password is required");
            //registerPassword.requestFocus();
            return false;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Please make sure the passwords are the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            registerPassword.setError("Password length must be more that 6");
            registerPassword.requestFocus();
            return false;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Sucessful", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Unsucessful", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    return true;
    }




    @OnClick(R.id.register_user)
    public void onViewClicked() {




        if (registerUser() == true)
        {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            //startActivity(new Intent(this, MainActivity.class));
            //Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
        }
    }


}
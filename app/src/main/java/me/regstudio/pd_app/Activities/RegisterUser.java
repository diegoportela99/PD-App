package me.regstudio.pd_app.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

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

    }



    private void registerUser() {
        String email = registerEmail.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String rePassword = registerRePassword.getText().toString().trim();
        if (email == null) {
            registerEmail.setError("Email is required");
            registerEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerEmail.setError("Please enter a valid email");
            registerRePassword.requestFocus();
            return;
        }

        if (password == null) {
            registerPassword.setError("Password is required");
            registerPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            registerPassword.setError("Password length must be more that 6");
            registerPassword.requestFocus();
            return;
        }
    }

    @OnClick(R.id.register_user)
    public void onViewClicked() {

    }

    //@OnClick(R.id.register_user)
   // public void onViewClicked() {
    //}
}
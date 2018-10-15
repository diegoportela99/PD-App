package me.regstudio.pd_app.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.regstudio.pd_app.R;

/**
 * Class: LoginActivity
 * Extends: {@link AppCompatActivity}
 * Author: Carlos Tirado < Carlos.TiradoCorts@uts.edu.au> and YOU!
 * Description:
 * <p>
 * Welcome to the first class in the project. I will be leaving some comments like this through all
 * the classes I write in order to help you get a hold on the project. Here I took the liberty of
 * creating an empty Log In activity for you to fill in the details of how your log in is
 * gonna work. Please, Modify Accordingly!
 * <p>
 */
public class LoginActivity extends AppCompatActivity {


    /**
     * Use the @BindView annotation so Butter Knife can search for that view, and cast it for you
     * (in this case it will get casted to Edit Text)
     */


    /**
     * If you want to know more about Butter Knife, please, see the link I left at the build.gradle
     * file.
     */


    @BindView(R.id.register)
    Button register;
    /**
     * It is helpful to create a tag for every activity/fragment. It will be easier to understand
     * log messages by having different tags on different places.
     */
    private static String TAG = "LoginActivity";
    @BindView(R.id.login_email)
    EditText loginEmail;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.doctor_login)
    Button doctorLogin;
    @BindView(R.id.forgotPassword)
    Button forgotPassword;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // You need this line on your activity so Butter Knife knows what Activity-View we are referencing
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        // A reference to the toolbar, th  at way we can modify it as we please
        //Toolbar toolbar = findViewById(R.id.login_toolbar);
        //setSupportActionBar(toolbar);

        // Please try to use more String resources (values -> strings.xml) vs hardcoded Strings.
        setTitle(R.string.login_activity_title);

    }


    /**
     * See how Butter Knife also lets us add an on click event by adding this annotation before the
     * declaration of the function, making our life way easier.
     */

    private boolean userLogin() {
        String username = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

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
            Toast.makeText(this, "Please enter a password more than 6 letters", Toast.LENGTH_SHORT).show();
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


    @OnClick(R.id.login_btn)
    public void LogIn() {

        if (userLogin() == true) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.register)
    public void registerUser() {
        Intent intent1 = new Intent(this, RegisterUser.class);
        startActivity(intent1);

    }

    @OnClick(R.id.doctor_login)
    public void doctorLogin() {
        Intent intent2 = new Intent(this, DoctorLogin.class);
        startActivity(intent2);
    }

    @OnClick(R.id.forgotPassword)
    public void forgotPassword() {
        Intent intent12 = new Intent(this, RegisterUser.class);
        startActivity(intent12);
    }
}


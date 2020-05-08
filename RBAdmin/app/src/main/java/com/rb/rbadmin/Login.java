package com.rb.rbadmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rb.rbadmin.util.Validator;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private EditText email, password;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.loginBtn);
        TextView signUpLink = findViewById(R.id.signUpLink);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validator.validateEmail(email, "Please enter valid Email Address") && Validator.validateET(password, "Please enter valid Password"))
                    loginAccount();
            }
        });



        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    private void loginAccount() {
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Log.d("Success", "createUserWithEmail:success");

                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user != null)
                        startActivity(new Intent(Login.this, Home.class));

                } else {
                    Log.w("Fail", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Login.this, "Authentication failed." + Objects.requireNonNull(task.getException()).getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(Login.this, Home.class));
        }
    }
}

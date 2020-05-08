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

public class Register extends AppCompatActivity {
    private EditText email, password, repassword;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView signInLink = findViewById(R.id.signInLink);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        Button regsiter = findViewById(R.id.registerBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        regsiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validator.validateEmail(email, "Please enter valid Email Address") && Validator.validatePass(password, repassword, "Passwords do not match"))
                    createAccount();
            }
        });
    }

    private void createAccount() {
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Log.d("Success", "createUserWithEmail:success");
                    Toast.makeText(Register.this, "Authentication Success.",
                            Toast.LENGTH_SHORT).show();

                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user != null)
                        startActivity(new Intent(Register.this, Login.class));

                } else {
                    Log.w("Fail", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Register.this, "Authentication failed." + Objects.requireNonNull(task.getException()).getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

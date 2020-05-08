package com.rb.rashanbazzar.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rb.rashanbazzar.R;
import com.rb.rashanbazzar.model.User;

import java.util.Objects;

public class Profile extends AppCompatActivity {

    EditText name, email, city;
    Button submit;


    private String userId = null;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        submit = findViewById(R.id.submit);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDatabase();
            }
        });
    }

    private void addToDatabase() {
        String nameText = name.getText().toString().trim();
        String emailText = email.getText().toString().trim();
        String cityText = city.getText().toString().trim();

        User user = new User(emailText, nameText, cityText);
        databaseReference.child(userId).setValue(user);

        Toast.makeText(Profile.this, "Profile updated", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Profile.this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);

    }
}

package com.rb.rashanbazzar.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rb.rashanbazzar.R;
import com.rb.rashanbazzar.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class PlaceOrder extends AppCompatActivity {

    EditText title, quantity, note, category, address;
    Button placeOrder;

    String categoryText = null ,userId = null;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        categoryText = Objects.requireNonNull(getIntent().getExtras()).getString("category");
        databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
        userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        title = findViewById(R.id.title);
        quantity = findViewById(R.id.quantity);
        note = findViewById(R.id.note);
        placeOrder = findViewById(R.id.placeOrder);
        category = findViewById(R.id.category);
        address = findViewById(R.id.address);

        category.setText(categoryText);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDB();
            }
        });
    }

    private void addToDB() {
        String titleText = title.getText().toString().trim();
        String quantityText = quantity.getText().toString().trim();
        String noteText = note.getText().toString().trim();
        String addressText = address.getText().toString().trim();
        String fee = "10";

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String datePlaced = df.format(new Date());

        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        if(Integer.parseInt(currentTime.split(":")[0]) > 20){
            fee = "15";
        }

        Order order = new Order(titleText, quantityText, noteText, categoryText, datePlaced, addressText, fee, "");

        databaseReference.child(userId).push().setValue(order);
        Toast.makeText(PlaceOrder.this, "Order Placed", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(PlaceOrder.this, Home.class));
    }
}

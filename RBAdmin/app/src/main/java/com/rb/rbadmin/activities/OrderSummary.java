package com.rb.rbadmin.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rb.rbadmin.R;
import com.rb.rbadmin.fragments.OrderFragment;
import com.rb.rbadmin.model.Order;

public class OrderSummary extends AppCompatActivity {
    private Button deliver;
    private Button completeDelivery;

    private String orderKey, userKey;

    public OrderSummary() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        int orderId = getIntent().getIntExtra("id", -1);
        Order order = OrderFragment.getOrder(orderId);

        orderKey = OrderFragment.getOrderKey(orderId);
        userKey = OrderFragment.getUserKey(orderId);

        Log.d("Key_O", orderKey);
        Log.d("Key_U", userKey);

        TextView title = findViewById(R.id.title);
        TextView address = findViewById(R.id.deliveryAddress);
        TextView category = findViewById(R.id.category);
        TextView status = findViewById(R.id.status);
        TextView datePlaced = findViewById(R.id.datePlaced);
        TextView fee = findViewById(R.id.fee);
        deliver = findViewById(R.id.deliver);
        completeDelivery = findViewById(R.id.completeDelivery);
        Button cancelOrder = findViewById(R.id.cancel);

        if (order != null) {
            title.setText(order.getTitle());
            address.setText(order.getAddress());
            category.setText(order.getCategory());
            datePlaced.setText(order.getDatePlaced());
            fee.setText(String.format("₹%s", order.getFee()));

            String msg = "The order with " + order.getCategory() + " needs to be delivered.";

            status.setText(msg);
        }

        deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This order is now opt for delivering", Toast.LENGTH_SHORT).show();
                deliver.setEnabled(false);
                deliver.setAlpha(0);

                completeDelivery.setEnabled(true);
                completeDelivery.setAlpha(1);
            }
        });

        completeDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This order is now delivered", Toast.LENGTH_SHORT).show();
                completeDelivery.setAlpha(0);
                completeDelivery.setEnabled(false);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
                databaseReference.child(userKey).child(orderKey).child("delivered").setValue("Y");
            }
        });

        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliver.setAlpha(0);
                completeDelivery.setAlpha(0);

                createDialog();
            }
        });
    }

    private void createDialog() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(OrderSummary.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_reason, null);

        final EditText reason = view.findViewById(R.id.reason);
        Button done = view.findViewById(R.id.done);

        mBuilder.setView(view);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reasonText = reason.getText().toString().trim();
                updateDB(reasonText);
                dialog.dismiss();
            }
        });
    }

    private void updateDB(String reasonText) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
        databaseReference.child(userKey).child(orderKey).child("reason").setValue(reasonText);
        databaseReference.child(userKey).child(orderKey).child("delivered").setValue("C");
        Toast.makeText(getApplicationContext(), "This order is now cancelled", Toast.LENGTH_SHORT).show();
    }
}
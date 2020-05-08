package com.rb.rashanbazzar.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rb.rashanbazzar.R;
import com.rb.rashanbazzar.fragments.OrdersFragment;
import com.rb.rashanbazzar.model.Order;

public class OrderSummary extends AppCompatActivity {

    public OrderSummary() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        int orderId = getIntent().getIntExtra("id", -1);
        Order order = OrdersFragment.getOrder(orderId);

        TextView title = findViewById(R.id.title);
        TextView address = findViewById(R.id.deliveryAddress);
        TextView category = findViewById(R.id.category);
        TextView status = findViewById(R.id.status);
        TextView datePlaced = findViewById(R.id.datePlaced);
        TextView fee = findViewById(R.id.fee);
        TextView reason = findViewById(R.id.reason);

        if (order != null) {
            title.setText(order.getTitle());
            address.setText(order.getAddress());
            category.setText(order.getCategory());
            datePlaced.setText(order.getDatePlaced());
            fee.setText(String.format("₹%s", order.getFee()));

            String statusText = order.getDelivered();
            String msg;
            if (statusText.equals("Y")) {
                msg = "Your order with " + order.getCategory() + " was delivered.";
            } else if (statusText.equals("N")){
                msg = "Your order with " + order.getCategory() + " is not yet delivered.";
            } else{
                msg = "Your order with " + order.getCategory() + " was cancelled.";
                String reasonMsg = "Reason : " + order.getReason();
                reason.setText(reasonMsg);
            }
            status.setText(msg);
        }


        findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(OrderSummary.this, new String[]{Manifest.permission.CALL_PHONE},1);
                        return;
                    }
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:911514875623")));
            }
        });
    }
}

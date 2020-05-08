package com.rb.rbadmin.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rb.rbadmin.R;
import com.rb.rbadmin.fragments.HistoryFragment;
import com.rb.rbadmin.model.Order;

public class HistorySummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_summary);

        int orderId = getIntent().getIntExtra("id", -1);
        Order order = HistoryFragment.getOrder(orderId);

        TextView title = findViewById(R.id.title);
        TextView address = findViewById(R.id.deliveryAddress);
        TextView category = findViewById(R.id.category);
        TextView status = findViewById(R.id.status);
        TextView datePlaced = findViewById(R.id.datePlaced);
        TextView fee = findViewById(R.id.fee);

        if (order != null) {
            title.setText(order.getTitle());
            address.setText(order.getAddress());
            category.setText(order.getCategory());
            datePlaced.setText(order.getDatePlaced());
            fee.setText(String.format("₹%s", order.getFee()));

            String msg;
            if(order.getDelivered().equals("C"))
                msg = "The order with " + order.getCategory() + " was cancelled.";
            else
                msg = "The order with " + order.getCategory() + " was delivered.";

            status.setText(msg);
        }

    }
}

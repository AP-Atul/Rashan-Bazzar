package com.rb.rashanbazzar.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rb.rashanbazzar.R;
import com.rb.rashanbazzar.customer.OrderSummary;
import com.rb.rashanbazzar.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.category.setText(orderList.get(position).getCategory());
        holder.title.setText(orderList.get(position).getTitle());
        holder.datePlaced.setText(orderList.get(position).getDatePlaced());
        holder.delivery.setText(orderList.get(position).getDelivered());

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrderSummary.class).putExtra("id", position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView category, title, datePlaced, delivery;
        private RelativeLayout rl;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);
            title = itemView.findViewById(R.id.title);
            datePlaced = itemView.findViewById(R.id.datePlaced);
            delivery = itemView.findViewById(R.id.delivery);
            rl = itemView.findViewById(R.id.rl);
        }
    }
}
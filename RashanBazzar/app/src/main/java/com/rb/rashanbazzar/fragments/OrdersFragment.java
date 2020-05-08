package com.rb.rashanbazzar.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rb.rashanbazzar.R;
import com.rb.rashanbazzar.adapters.OrderAdapter;
import com.rb.rashanbazzar.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressbar;

    private static List<Order> orderList = new ArrayList<>();
    private RecyclerView.Adapter adapter;


    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        progressbar = view.findViewById(R.id.progressbar);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.VISIBLE);
                orderList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Order order = snapshot.getValue(Order.class);

//                    Log.i("CLASS", order.getTitle());
                    orderList.add(order);
                }

                adapter = new OrderAdapter(getContext(), orderList);
                recyclerView.setAdapter(adapter);
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressbar.setVisibility(View.GONE);
            }
        });


        return view;
    }

    public static Order getOrder(int id){
        return orderList.get(id);
    }

}

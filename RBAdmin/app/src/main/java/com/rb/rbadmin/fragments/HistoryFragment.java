package com.rb.rbadmin.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rb.rbadmin.R;
import com.rb.rbadmin.adapters.OrderAdapter;
import com.rb.rbadmin.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressbar;

    private static List<Order> orderList = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        progressbar = view.findViewById(R.id.progressbar);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.VISIBLE);
                orderList.clear();

                for(DataSnapshot snapshot1 : dataSnapshot.getChildren()){
                    for(DataSnapshot snapshot : snapshot1.getChildren()) {

                        Order order = snapshot.getValue(Order.class);

                        if (order != null && !order.getDelivered().equals("N"))
                            orderList.add(order);
                    }
                }

                adapter = new OrderAdapter(getContext(), orderList, "h");
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

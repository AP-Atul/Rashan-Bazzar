package com.rb.rashanbazzar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.rb.rashanbazzar.R;
import com.rb.rashanbazzar.customer.PlaceOrder;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        RelativeLayout groceries = view.findViewById(R.id.groceries);
        RelativeLayout medicines = view.findViewById(R.id.medicine);
        RelativeLayout stationary = view.findViewById(R.id.stationary);


        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PlaceOrder.class).putExtra("category", "Groceries"));
            }
        });

        medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PlaceOrder.class).putExtra("category", "Medicines"));
            }
        });


        stationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PlaceOrder.class).putExtra("category", "Stationary"));
            }
        });



        return view;
    }

}

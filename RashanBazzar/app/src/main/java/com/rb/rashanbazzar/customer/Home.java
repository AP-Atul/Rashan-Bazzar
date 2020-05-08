package com.rb.rashanbazzar.customer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.rb.rashanbazzar.R;
import com.rb.rashanbazzar.fragments.HomeFragment;
import com.rb.rashanbazzar.fragments.OrdersFragment;
import com.rb.rashanbazzar.fragments.ProfileFragment;

public class Home extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    private final int TAB_HOME = 1;
    private final int TAB_ORDERS = 2;
    private final int TAB_PROFILE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new HomeFragment());

        bottomNavigation = findViewById(R.id.bottomNav);
        bottomNavigation.add(new MeowBottomNavigation.Model(TAB_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(TAB_ORDERS, R.drawable.ic_orders));
        bottomNavigation.add(new MeowBottomNavigation.Model(TAB_PROFILE, R.drawable.ic_profile));


        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                // your codes
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch(item.getId()){

                    case TAB_HOME:
                        fragment = new HomeFragment();
                        break;

                    case TAB_ORDERS:
                        fragment = new OrdersFragment();
                        break;

                    case TAB_PROFILE:
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch(item.getId()){

                    case TAB_HOME:
                        fragment = new HomeFragment();
                        break;

                    case TAB_ORDERS:
                        fragment = new OrdersFragment();
                        break;

                    case TAB_PROFILE:
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });


        bottomNavigation.show(TAB_HOME, false);
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}

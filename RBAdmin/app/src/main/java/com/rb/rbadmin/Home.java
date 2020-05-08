package com.rb.rbadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.rb.rbadmin.fragments.HistoryFragment;
import com.rb.rbadmin.fragments.OrderFragment;
import com.rb.rbadmin.fragments.ProfileFragment;

public class Home extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    private final int TAB_ORDERS = 1;
    private final int TAB_HISTORY = 2;
    private final int TAB_PROFILE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new OrderFragment());

        bottomNavigation = findViewById(R.id.bottomNav);
        bottomNavigation.add(new MeowBottomNavigation.Model(TAB_ORDERS, R.drawable.ic_orders));
        bottomNavigation.add(new MeowBottomNavigation.Model(TAB_HISTORY, R.drawable.ic_history));
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

                    case TAB_ORDERS:
                        fragment = new OrderFragment();
                        break;

                    case TAB_HISTORY:
                        fragment = new HistoryFragment();
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

                    case TAB_ORDERS:
                        fragment = new OrderFragment();
                        break;

                    case TAB_HISTORY:
                        fragment = new HistoryFragment();
                        break;

                    case TAB_PROFILE:
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.show(TAB_ORDERS, false);

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

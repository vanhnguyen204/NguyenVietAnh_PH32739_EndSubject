package com.fpoly.nguyenvietanh_ph32739;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        FragmentAdapter adapter = new FragmentAdapter(MainActivity.this);

        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("DS LAPTOP");
                        break;
                    case 1: {
                        tab.setText("Thêm LAPTOP");
                    }
                    default:
                        break;
                }
            }
        }).attach();
    }

}

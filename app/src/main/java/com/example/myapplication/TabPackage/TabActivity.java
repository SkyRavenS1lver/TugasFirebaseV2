package com.example.myapplication.TabPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.TabMakanan.MakananAdapter;
import com.example.myapplication.TabMinuman.MinumanAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabActivity extends AppCompatActivity {
    public TabLayoutMediator mediator;
    public static RecyclerView rvMakanan;
    public static RecyclerView rvMinuman;
    public TabLayout tabLayout;
    public ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tabLayout =findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.pager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);
        mediator = new TabLayoutMediator(tabLayout,viewPager2, ((tab, position) -> {
            if (position == 0){
                tab.setText("Makanan");
            }
            else if (position == 1){
                tab.setText("Minuman");
            }
        }));
        mediator.attach();
    }
}
package com.example.myapplication.TabPackage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.TabMinuman.Activity.ReadDataMinuman;
import com.example.myapplication.TabMakanan.Activity.ReadDataMakanan;

public class TabAdapter extends FragmentStateAdapter {
    public TabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            ReadDataMakanan fragment1 = new ReadDataMakanan();
            return fragment1;
        }
        else{
            ReadDataMinuman fragment2 = new ReadDataMinuman();
            return fragment2;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

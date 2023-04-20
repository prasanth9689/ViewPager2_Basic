package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.viewpager.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Context mContext = this;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    ViewPagerFragmentAdapter adapter;

    // array for tab labels
    private String[] tabs = new String[]{"TAB 1", "TAB 2", "TAB 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Objects.requireNonNull(getSupportActionBar()).hide();
      //  getSupportActionBar().show();

        // call function to initialize views
        init();

        // bind and set tabLayout to viewPager2 and set labels for every tab
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(tabs[position]);
        }).attach();

        // set default position to 1 instead of default 0
        viewPager2.setCurrentItem(1, false);
    }

    private void init() {
        // initialize tabLayout
        tabLayout = findViewById(R.id.tab_layout);
        // initialize viewPager2
        viewPager2 = findViewById(R.id.view_pager2);
        // create adapter instance
        adapter = new ViewPagerFragmentAdapter(this);
        // set adapter to viewPager2
        viewPager2.setAdapter(adapter);

        // remove default elevation of actionbar
        getSupportActionBar().setElevation(0);
    }

    // create adapter to attach fragments to viewpager2 using FragmentStateAdapter
    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        // return fragments at every position
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new fragment_one(); // calls fragment
                case 1:
                    return new fragment_two(); // chats fragment
                case 2:
                    return new fragment_three(); // status fragment
            }
            return new fragment_two(); //chats fragment
        }

        // return total number of tabs in our case we have 3
        @Override
        public int getItemCount() {
            return tabs.length;
        }
    }
}
package com.solace.covidaftereffect;

import androidx.annotation.NonNull;
import androidx.appcompat   .app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DaysActivity extends AppCompatActivity {
    private PrefManager prefManager;
    public Button btnSKIP, btnNEXT;
    private static final int NUM_PAGES = 3;
    //The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps.
    public static ViewPager2 viewPager;
    // The pager adapter, which provides the pages to the view pager widget.
    private FragmentStateAdapter pagerAdapter;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
       /* prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }*/

        btnSKIP = (Button) findViewById(R.id.button_skip);
        btnSKIP = (Button) findViewById(R.id.button_next);

        viewPager = findViewById(R.id.mypager);
        pagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> tab.setText(titles[position])).attach();

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            }
        }).attach();
        Button btnSkip, btnNext;
        btnSkip = (Button) findViewById(R.id.button_skip);
        btnNext = (Button) findViewById(R.id.button_next);
       /* btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();

            }
        });      */
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = (viewPager.getCurrentItem() + 1);
                if (current < NUM_PAGES) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                   // launchHomeScreen();
                }
            }


        });
    }

   /* private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(DaysActivity.this, MainActivity.class));
        finish();
    }*/

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.d
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public class MyPagerAdapter extends FragmentStateAdapter {

        public MyPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int pos) {
            switch (pos) {
                case 0: {
                    return TestDayOne.newInstance("fragment 1");

                }
                case 1: {

                    return TestDayTwo.newInstance("fragment 2");
                }
                case 2: {

                    return TestDayThree.newInstance("fragment 3");
                }
                default:
                    return TestDayOne.newInstance("fragment 1, Default");
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }

    }

}

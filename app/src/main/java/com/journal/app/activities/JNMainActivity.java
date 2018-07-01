package com.journal.app.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.journal.app.JNAsyncListener;
import com.journal.app.fragments.JNDiaryFragment;
import com.journal.app.fragments.JNUserDiaryHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class JNMainActivity extends JNBaseActivity implements JNAsyncListener {

    private static final String clazzz = JNMainActivity.class.getName();
    private BottomNavigationView tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private JNUserDiaryHistory.JNHistoryAdapter adapter1;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jn_main_activity);
        FirebaseApp.initializeApp(this);
        viewPager = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.toolbar);
        button = findViewById(R.id.get_started);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Log.e("BEFORE Action Bar", "firstLog");
        if (mDrawerToggle != null) {
            mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view == null) {
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    } else {
                        mDrawerLayout.closeDrawer(GravityCompat.END);

                    }
                }
            });

        }
        Log.e("After Action Bar", "AfterrLog");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                // getActionBar().setTitle("");
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                mDrawerToggle.getToolbarNavigationClickListener();
                mDrawerLayout.addDrawerListener(mDrawerToggle);
                mDrawerLayout.closeDrawer(GravityCompat.END);

            }

            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle("MIDAS");

                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();      // creates call to onPrepareOptionsMenu()
                mDrawerToggle.getToolbarNavigationClickListener();
                mDrawerLayout.addDrawerListener(mDrawerToggle);
                mDrawerLayout.openDrawer(GravityCompat.START);

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case android.R.id.home:
                        mDrawerLayout.openDrawer(GravityCompat.START);
                        return true;
                    case R.id.menu_service:

                        showCreateDiary();
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.menu_activity:
                        showDiaryHistory();
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.menu_account:
                       straightLogout();
                        mDrawerLayout.closeDrawers();
                        return true;

                }
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);

            }
        });

    }




    private void showCreateDiary() {
        JNDiaryFragment frag = new JNDiaryFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();

    }




    private void showDiaryHistory() {
        JNUserDiaryHistory frag = new JNUserDiaryHistory();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, frag)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onTaskCompleted(Queue<? extends JNUserDiaryHistory.User> data) {
       /* JNUserDiaryHistory activitiesFragment = (JNUserDiaryHistory) adapter1.getItem(1)
        activitiesFragment.onCollectionDataLoadFinished(data);*/
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public List<String> getTitles() {
            return mFragmentTitleList;
        }
    }
}

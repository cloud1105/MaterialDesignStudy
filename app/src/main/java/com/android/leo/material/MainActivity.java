package com.android.leo.material;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.leo.material.fragment.FirstFragment;
import com.android.leo.material.fragment.SecondFragment;
import com.android.leo.material.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity {
    private NavigationView nav;
    private DrawerLayout drawerLayout;
    private TextInputLayout textInputLayout;
    private Button btnOk;
    private EditText editText;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Fab", Snackbar.LENGTH_SHORT).setAction("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "click snackbar cancel", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        nav = (NavigationView) findViewById(R.id.nav);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        textInputLayout = (TextInputLayout) findViewById(R.id.til_edit);
        editText = (EditText) findViewById(R.id.edit_name);
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null && s.length() < 6) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("name should have six characters!");
                    } else {
                        textInputLayout.setErrorEnabled(false);
                    }
                }
            });
        }
        btnOk = (Button) findViewById(R.id.btn_ok);
        if (btnOk != null) {
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(textInputLayout.getEditText().getText())) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("empty text!");
                    } else if (editText.getText().length() < 6) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("name should have six characters!");
                    } else {
                        textInputLayout.setErrorEnabled(false);
                    }
                }
            });
        }

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "click home", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this, "click friends", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_discussion:
                        Toast.makeText(MainActivity.this,"click discussion",Toast.LENGTH_SHORT).show();
                    case R.id.nav_messages:
                        Toast.makeText(MainActivity.this,"click message",Toast.LENGTH_SHORT).show();
                    default:
                        return false;
                }
            }
        });


        //join drawerlayout and toolbar
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        viewpager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        MyFixedPagerAdapter adapter = new MyFixedPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

    }

    private class MyFixedPagerAdapter extends FragmentPagerAdapter {

        public MyFixedPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                default:
                    return new FirstFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "First";
                case 1:
                    return "Second";
                case 2:
                    return "Third";
                default:
                    return "First";
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}

package com.example.hotelmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
 {
    String provider;
    private static final int SECOND_ACTIVITY_REQUESTCODE = 2;
    private static final int THIRD_ACTIVITY_REQUESTCODE = 3;
    private Button mButtonLogin, mButtonRegister;
    private ViewPager pager1, pager2;
    private ImageView mImageButton, mCovidInfo;



    private int x = 0;
    int currentPage1 = 0;
    int currentPage2 = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Hotel management");

        ContactUsDialog mdialog = new ContactUsDialog();
        mButtonLogin = (Button) findViewById(R.id.buttonlogin);
        mButtonRegister = (Button) findViewById(R.id.buttonregister);
        pager1 = (ViewPager) findViewById(R.id.viewpager);
        pager2 = (ViewPager) findViewById(R.id.viewpager2);
        mImageButton = (ImageView) findViewById(R.id.imageButton3);
        mCovidInfo = (ImageView) findViewById(R.id.covidinfoview);
        pager1.setAdapter(new MyPagerAdapter1(getSupportFragmentManager()));
        pager2.setAdapter(new MyPagerAdapter2(getSupportFragmentManager()));
        move(pager1);


        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getApplicationContext(), mImageButton);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.actions1, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.contact:
                                mdialog.show(getSupportFragmentManager(), "Contact us");
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent nextAct = new Intent(MainActivity.this, Loginpage.class);
                startActivityForResult(nextAct, SECOND_ACTIVITY_REQUESTCODE);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextAct = new Intent(MainActivity.this, Registerpage.class);
                startActivityForResult(nextAct, THIRD_ACTIVITY_REQUESTCODE);
            }
        });
        mCovidInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainCovidInfo.class);
                startActivityForResult(intent, 7);
            }
        });

        /* the following is used to create an automatic slide with viewpager
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage1 == 4 - 1) {
                    currentPage1 = 0;

                }
                if (currentPage2 == 5 - 1) {
                    currentPage2 = 0;
                }
                pager2.setCurrentItem(currentPage1++, true);
                pager1.setCurrentItem(currentPage2++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


    }


    public void move(View view) {

        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        view.startAnimation(animation1);

    }




    private class MyPagerAdapter1 extends FragmentPagerAdapter {

        public MyPagerAdapter1(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 1:
                    return frag1_main.newInstance("WEAR A MASK");
                case 2:

                    return frag1_main.newInstance("CLEAN YOUR HANDS");
                case 3:

                    return frag1_main.newInstance("BE SAFE!");
                default:

                    return frag1_main.newInstance("IMPORTANT NOTICE");

            }

        }


        @Override
        public int getCount() {
            return 4;
        }
    }

    private class MyPagerAdapter2 extends FragmentPagerAdapter {

        public MyPagerAdapter2(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 1:

                    return frag2_main.newInstance(R.drawable.hotelschina, "China");
                case 2:

                    return frag2_main.newInstance(R.drawable.francehotel, "France");
                default:

                    return frag2_main.newInstance(R.drawable.dubaihotel, "Dubai");

            }

        }


        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onBackPressed() {
        System.out.println("not return");
    }


}
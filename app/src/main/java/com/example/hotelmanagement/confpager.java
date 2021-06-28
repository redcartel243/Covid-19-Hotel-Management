package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class confpager extends AppCompatActivity {

    String user;
    String rn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confpager);
        ViewPager pager = (ViewPager) findViewById(R.id.pager1);
        Intent intent=getIntent();
        user=intent.getStringExtra("user");
        rn=intent.getStringExtra("rn");

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int pos) {
            switch (rn) {
                case "101":
                    return RoomConfirmationFrag.newInstance(R.drawable.french,R.drawable.desone,102,user);
                case "102":

                    return RoomConfirmationFrag.newInstance(R.drawable.english,R.drawable.destwo,103,user);
                case "103":

                    return RoomConfirmationFrag.newInstance(R.drawable.computer,R.drawable.desthree,104,user);
                case "104":
                    return RoomConfirmationFrag.newInstance((R.drawable.food),R.drawable.desfour,105,user);
                default:

                    return RoomConfirmationFrag.newInstance(R.drawable.science,R.drawable.desfive,101,user);

            }

        }


        @Override
        public int getCount() {
            return 4;
        }
    }
}
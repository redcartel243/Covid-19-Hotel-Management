package com.example.hotelmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class MainCovidInfo extends AppCompatActivity{
    private ImageView mImageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covidinfomain);
        this.setTitle("Covid-19 prevention info");
        ContactEmergencydialog mdialog=new ContactEmergencydialog();
        mImageButton=(ImageView) findViewById(R.id.menu);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getApplicationContext(),mImageButton);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.action1, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.SandD:
                                Intent intent=new Intent(MainCovidInfo.this, MainCovidInfo2.class);
                                startActivityForResult(intent,1);
                                return true;
                            case R.id.representation:
                                Intent intent1=new Intent(MainCovidInfo.this, MainCovidInfo3.class);
                                startActivityForResult(intent1,1);
                                return true;
                            case R.id.contact:
                                mdialog.show(getSupportFragmentManager(),"Contact us");
                                return true;
                            case R.id.exit:
                                Intent intent2=new Intent(MainCovidInfo.this, MainActivity.class);
                                startActivityForResult(intent2,1);
                            default:
                                return false;
                        }
                    }
                });
            }
        });


    }


}
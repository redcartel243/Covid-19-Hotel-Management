package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.VideoView;

public class MainCovidInfo2 extends AppCompatActivity {

        private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptomsanddiagnosis);
        // initiate a video view
        VideoView simpleVideoView = (VideoView) findViewById(R.id.videoView);
        ContactEmergencydialog mdialog=new ContactEmergencydialog();
        mImageView=(ImageView)findViewById(R.id.menu2);
        // create an object of media controller
        MediaController mediaController = new MediaController(this);
// initiate a video view
// set media controller object for a video view
        simpleVideoView.setMediaController(mediaController);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.covidspread));
        mediaController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleVideoView.start();
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getApplicationContext(),mImageView);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.action1, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.main:
                                Intent intent=new Intent(MainCovidInfo2.this, MainCovidInfo.class);
                                startActivityForResult(intent,2);
                                return true;
                            case R.id.representation:
                                Intent intent1=new Intent(MainCovidInfo2.this, MainCovidInfo3.class);
                                startActivityForResult(intent1,1);
                                return true;
                            case R.id.contact:
                                mdialog.show(getSupportFragmentManager(),"Contact us");
                                return true;
                            case R.id.exit:
                                Intent intent2=new Intent(MainCovidInfo2.this, MainActivity.class);
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
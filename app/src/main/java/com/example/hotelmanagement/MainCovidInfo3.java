package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ZoomControls;

import com.github.chrisbanes.photoview.PhotoView;

public class MainCovidInfo3 extends AppCompatActivity{
    private ImageView mImageView;
    private PhotoView mPhotoView;
    private Button mButton1,mButton2;
    ZoomControls zoomControls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.representation);
        mImageView=(ImageView)findViewById(R.id.menu3);
        mPhotoView=(PhotoView) findViewById(R.id.imageView4);


        ContactEmergencydialog mdialog1=new ContactEmergencydialog();
        steponedialog mdialog2=new steponedialog();
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
                                Intent intent=new Intent(MainCovidInfo3.this, MainCovidInfo.class);
                                startActivityForResult(intent,2);
                                return true;
                            case R.id.SandD:
                                Intent intent1=new Intent(MainCovidInfo3.this, MainCovidInfo2.class);
                                startActivityForResult(intent1,3);
                                return true;
                            case R.id.contact:
                                mdialog1.show(getSupportFragmentManager(),"Contact us");
                                return true;
                            case R.id.exit:
                                Intent intent2=new Intent(MainCovidInfo3.this, MainActivity.class);
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
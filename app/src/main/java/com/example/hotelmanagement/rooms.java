package com.example.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sourceforge.jtds.jdbc.*;

public class rooms extends AppCompatActivity {

    private ListView mListView1;
    private Spinner mSpinner1,mSpinner2;
    private ImageView mImageButton;
    //the information must come from the databse besides the pictures of the rooms
    private String objects[]={"101","102","103","104","105"};
    private String state[]={};
    private String appuser;
    private Integer imageid[]={R.drawable.science,R.drawable.french,R.drawable.english,R.drawable.computer,R.drawable.food};
    private int counter=0;
    private boolean allowedtobook=true;
    String country;
    String clss;
    private boolean choosed=false;
    private static final int Fourth_ACTIVITY_REQUESTCODE = 4;
    private TextView currentloc;



    DatabaseHandlerOnline mBooking=new DatabaseHandlerOnline("check state");
    DatabaseHandlerOnline mCanBook=new DatabaseHandlerOnline("rooms");

    PopupWindow popUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        mListView1=(ListView)findViewById(R.id.listview1);
        mSpinner1=(Spinner)findViewById(R.id.spinner1);
        mSpinner2=(Spinner)findViewById(R.id.spinner2);
        currentloc=(TextView)findViewById(R.id.lcoation);



        List<String> unfold=new ArrayList<String>();
        unfold.add("Single rooms");
        unfold.add("Double rooms");
        unfold.add("Apartment");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,unfold);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner1.setAdapter(adapter);
        mSpinner1.setOnItemSelectedListener(ItemSelectedListener1);
        List<String> unfold1=new ArrayList<String>();
        unfold1.add("China");
        unfold1.add("France");
        unfold1.add("Dubai");
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,unfold1);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner2.setAdapter(adapter2);
        mSpinner2.setOnItemSelectedListener(ItemSelectedListener2);


        //we get the informations from the previous activity
        Intent intent=getIntent();

        state=intent.getStringArrayExtra("st");
        appuser=intent.getStringExtra("user");
        country=intent.getStringExtra("location");
        clss=intent.getStringExtra("activity");
        currentloc.setHint("Current location: "+country.toUpperCase());

        CustomImageList customRoomList = new CustomImageList(this, objects, state, imageid);
        mListView1.setAdapter(customRoomList);
        mImageButton=(ImageView)findViewById(R.id.imageButton4);
        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                switch (selectedItemText) {
                    //here we will open fragments if item clicked
                    case "101":



                        mBooking.setRoomNumber("101");
                        mBooking.execute("");
                        Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_SHORT).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                        if(mBooking.mBooked==false){
                            Intent intent1=new Intent(rooms.this,confpager.class);
                            intent1.putExtra("rn","101");
                            intent1.putExtra("user",appuser);
                            intent1.putExtra("location",country);
                            startActivityForResult(intent1,47);

                        }else{
                            Toast.makeText(rooms.this, "This room is not available", Toast.LENGTH_SHORT).show();
                        }

                        }
                        }, 2000);
                        break;
                    case "102":
                        System.out.println("102");

                            mBooking.setRoomNumber("102");
                            mBooking.execute("");
                        Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_SHORT).show();
                        Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            if(mBooking.mBooked==false){
                                Intent intent1=new Intent(rooms.this,confpager.class);
                                intent1.putExtra("user",appuser);
                                intent1.putExtra("rn","102");
                                intent1.putExtra("location",country);
                                startActivityForResult(intent1,47);

                            }else{
                                Toast.makeText(rooms.this, "This room is not available", Toast.LENGTH_SHORT).show();
                            }

                            counter=0;
                        }
                        }, 2000);
                        break;
                    case "103":
                        mBooking.setRoomNumber("103");
                        mBooking.execute("");
                        Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_SHORT).show();
                        Handler handler3 = new Handler();
                        handler3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            if(mBooking.mBooked==false){
                                Intent intent1=new Intent(rooms.this,confpager.class);
                                intent1.putExtra("user",appuser);
                                intent1.putExtra("rn","103");
                                intent1.putExtra("location",country);
                                startActivityForResult(intent1,47);
                            }else{
                                Toast.makeText(rooms.this, "This room is not available", Toast.LENGTH_SHORT).show();
                            }

                            counter=0;
                        }
                        }, 2000);
                        break;
                    case "104":
                        mBooking.setRoomNumber("104");
                        mBooking.execute("");
                        Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_SHORT).show();
                        Handler handler4 = new Handler();
                        handler4.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            if(mBooking.mBooked==false){
                                Intent intent1=new Intent(rooms.this,confpager.class);
                                intent1.putExtra("user",appuser);
                                intent1.putExtra("rn","104");
                                intent1.putExtra("location",country);
                                startActivityForResult(intent1,47);

                            }else{
                                Toast.makeText(rooms.this, "This room is not available", Toast.LENGTH_SHORT).show();
                            }

                        }}, 2000);
                        break;
                    case "105":
                        mBooking.setRoomNumber("105");
                        mBooking.execute("");
                        Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_SHORT).show();
                        Handler handler5 = new Handler();
                        handler5.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            if(mBooking.mBooked==false){
                                Intent intent1=new Intent(rooms.this,confpager.class);
                                intent1.putExtra("user",appuser);
                                intent1.putExtra("rn","105");
                                intent1.putExtra("location",country);
                                startActivityForResult(intent1,47);

                            }else{
                                Toast.makeText(rooms.this, "This room is not available", Toast.LENGTH_SHORT).show();
                            }

                        }}, 2000);
                        break;
                }

            }});

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getApplicationContext(),mImageButton);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.actions2, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.logout:
                               Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
                               startActivityForResult(intent2,43);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });
    }
    private AdapterView.OnItemSelectedListener ItemSelectedListener1=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedItemText=(String)parent.getItemAtPosition(position);
            switch (selectedItemText){
                case "Single rooms":
                    //something here
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(rooms.this, "Select something", Toast.LENGTH_SHORT).show();
        }
    };

    private AdapterView.OnItemSelectedListener ItemSelectedListener2=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedItemText=(String)parent.getItemAtPosition(position);
            switch (selectedItemText){
                case "China":
                    //something here
                    Toast.makeText(rooms.this,"you selected China",Toast.LENGTH_SHORT).show();
                    System.out.println(country);
                    if (!country.equals("cn")){
                        mListView1.setEnabled(false);
                    }
                    else{
                        mListView1.setEnabled(true);
                    }

                    break;
                case "France":
                    Toast.makeText(rooms.this,"you selected France",Toast.LENGTH_SHORT).show();
                    System.out.println(country);
                    if (!country.equals("fr")){
                        mListView1.setEnabled(false);
                    }
                    else{
                        mListView1.setEnabled(true);
                    }

                    break;
                case "Dubai" :
                    Toast.makeText(rooms.this,"you selected Dubai",Toast.LENGTH_SHORT).show();
                    System.out.println(country);
                    if (!country.equals("itu")){
                        mListView1.setEnabled(false);
                    }
                    else{
                        mListView1.setEnabled(true);
                    }

                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(rooms.this, "Select something", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    public void onBackPressed() {
        System.out.println("not return");
    }



}

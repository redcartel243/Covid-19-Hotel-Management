package com.example.hotelmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class Loginpage extends AppCompatActivity implements LocationListener {
    private static final int Fourth_ACTIVITY_REQUESTCODE = 4;
    private Button mButtonlogin = null;
    private EditText mUseName, mPassword;
    private String state[] = {};
    ConstraintLayout mConstraintLayout;
    ProgressBar mProgressBar;
    protected LocationManager locationManager;
    //this will send the location

    boolean getlocation = false;
    private Button mConfirm;
    private boolean testpass;
    private int counter = 0;
    String permissions[] = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    private Double latitude=30.27667403, longitude=120.103516;
    String provider;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy( Criteria.ACCURACY_COARSE);
        provider = locationManager.getBestProvider( criteria, true );
        provider = locationManager.getBestProvider(criteria, false);
        mButtonlogin = (Button) findViewById(R.id.buttonlogin2);
        mUseName = (EditText) findViewById(R.id.username2);
        mPassword = (EditText) findViewById(R.id.password2);
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.ConstraintLayout);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mButtonlogin.setEnabled(true);
        requestPermissions();
        //we initialize info to use in the next activity
        mButtonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mButtonlogin.setEnabled(false);
                    DatabaseHandlerOnline mconnectMySql1 = (DatabaseHandlerOnline) new DatabaseHandlerOnline("object").execute();
                    DatabaseHandlerOnline mconnectMySql2 = (DatabaseHandlerOnline) new DatabaseHandlerOnline("state").execute();
                    DatabaseHandlerOnline mconnectMySql3 = (DatabaseHandlerOnline) new DatabaseHandlerOnline("login", mUseName.getText().toString(), mPassword.getText().toString()).execute();
                    GetCountry mlocation = (GetCountry) new GetCountry(latitude, longitude).execute();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            state = mconnectMySql2.state;
                            testpass = mconnectMySql3.passlog;
                            System.out.println("ok" + testpass);
                            Intent nextAct = new Intent(Loginpage.this, rooms.class);
                            Intent prevact = new Intent(Loginpage.this, MainActivity.class);
                            //we send the information to the next activity
                            nextAct.putExtra("st", state);
                            nextAct.putExtra("user", mUseName.getText().toString());
                            nextAct.putExtra("location", mlocation.country);
                            nextAct.putExtra("activity","from login");
                            System.out.println(mconnectMySql3.passlog);
                            System.out.println(mlocation.country);

                            if (testpass == true&&mlocation!=null) {//user found
                                startActivityForResult(nextAct, Fourth_ACTIVITY_REQUESTCODE);
                            } else {
                                mButtonlogin.setEnabled(true);
                                mconnectMySql1.cancel(true);
                                mconnectMySql2.cancel(true);
                                mconnectMySql3.cancel(true);
                                mlocation.cancel(true);
                                Toast.makeText(Loginpage.this, "Please try again", Toast.LENGTH_SHORT).show();
                                counter++;
                                if (counter == 3) {

                                    startActivityForResult(prevact, 1);
                                }

                            }
                        }
                    }, 5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    @Override
    public void onLocationChanged(Location location) {

            System.out.println("here" + location.getLatitude());
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
            System.out.println(latitude+"and "+longitude);

    }
    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 99);
    }
    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
        System.out.println(provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
        System.out.println(provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
        System.out.println(provider);
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Permission")
                        .setMessage("This app needs your location")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(Loginpage.this,
                                        permissions,
                                        MY_PERMISSIONS_REQUEST_LOCATION);


                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        permissions,
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }



}

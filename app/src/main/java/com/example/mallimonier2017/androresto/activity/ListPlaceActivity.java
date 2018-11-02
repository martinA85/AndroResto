package com.example.mallimonier2017.androresto.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.mallimonier2017.androresto.R;
import com.example.mallimonier2017.androresto.adaptater.PlaceAdapter;
import com.example.mallimonier2017.androresto.dao.PlaceDao;

public class ListPlaceActivity extends AppCompatActivity implements LocationListener {

    private PlaceAdapter adapter;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE = 88;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE = 89;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);
        final RecyclerView recyclerView = findViewById(R.id.RecyclerViewPlaces);
        final PlaceDao dao = new PlaceDao(this);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Log.d("locationLog", "Log : ");
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this ,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }

        adapter = new PlaceAdapter(dao.selectAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        BottomNavigationView navBar = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intentToMapsActivity = new Intent(getApplicationContext(), MapsActivity.class);
                        getApplicationContext().startActivity(intentToMapsActivity);
                        return true;
                    case R.id.navigation_dashboard:
                        Intent intentToListActivity = new Intent(getApplicationContext(), ListPlaceActivity.class);
                        getApplicationContext().startActivity(intentToListActivity);
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.d("LocationLog", "OnLocationChanged");
        adapter.updatePosition(location.getLatitude(), location.getLongitude());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    public void OnRequestPermissionsResultCallback(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_COARSE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }

        }
    }
}

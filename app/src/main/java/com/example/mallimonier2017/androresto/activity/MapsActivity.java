package com.example.mallimonier2017.androresto.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.mallimonier2017.androresto.R;
import com.example.mallimonier2017.androresto.dao.PlaceDao;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Context context;
    private static final String TAG = "MyActivity";
    private GoogleMap mMap;
    PlaceDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getApplicationContext();
        dao = new PlaceDao(context);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.frag);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(com.google.android.gms.location.places.Place place) {
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                Log.w(TAG,"Ici");
                dao.insert(new com.example.mallimonier2017.androresto.model.Place(
                        place.getName().toString(),
                        place.getAddress().toString(),
                        Double.toString(place.getLatLng().latitude),
                        Double.toString(place.getLatLng().longitude),
                        place.getRating(),
                        place.getId()));
            }

            @Override
            public void onError(Status status) {
                Log.w(TAG,status.toString());
            }
        });

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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

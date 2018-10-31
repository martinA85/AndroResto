package com.example.mallimonier2017.androresto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.mallimonier2017.androresto.R;
import com.example.mallimonier2017.androresto.adaptater.PlaceAdapter;
import com.example.mallimonier2017.androresto.dao.PlaceDao;

public class ListPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);
        final RecyclerView recyclerView = findViewById(R.id.RecyclerViewPlaces);
        final PlaceDao dao = new PlaceDao(this);
        final PlaceAdapter adapter = new PlaceAdapter(dao.selectAll());
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
}

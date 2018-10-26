package com.example.mallimonier2017.androresto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mallimonier2017.androresto.R;
import com.example.mallimonier2017.androresto.adaptater.PlaceAdapter;

public class ListPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);
        final RecyclerView recyclerView = findViewById(R.id.RecyclerViewPlaces);
        final PlaceAdapter adapter = new PlaceAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}

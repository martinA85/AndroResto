package com.example.mallimonier2017.androresto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mallimonier2017.androresto.R;
import com.example.mallimonier2017.androresto.model.Place;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Place placeDetail = getIntent().getParcelableExtra("placeSelect");
        TextView title = findViewById(R.id.textViewTitleDetail);
        TextView adresse = findViewById(R.id.textViewAdresseDetail);
        title.setText(placeDetail.getName());
        adresse.setText(placeDetail.getAddress());
    }
}

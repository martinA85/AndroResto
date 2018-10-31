package com.example.mallimonier2017.androresto.adaptater;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mallimonier2017.androresto.R;
import com.example.mallimonier2017.androresto.model.Place;

import java.util.ArrayList;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private ArrayList<Place> listPlace = new ArrayList<Place>();

    public PlaceAdapter(ArrayList<Place> placeList){
        this.listPlace = placeList;
    }

    @NonNull
    @Override
    public PlaceAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewElementList = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item_view, parent, false);
        return new PlaceViewHolder(viewElementList);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        final Place placeToShow = listPlace.get(position);
        holder.textViewName.setText(placeToShow.getName());
        holder.textViewAddresse.setText(placeToShow.getAddress());
        holder.ratingBarRate.setRating(placeToShow.getRating());
    }

    @Override
    public int getItemCount() {
        return listPlace.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewDistance;
        TextView textViewAddresse;
        RatingBar ratingBarRate;


        public PlaceViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewNameItem);
            textViewDistance = itemView.findViewById(R.id.textViewDistance);
            textViewAddresse = itemView.findViewById(R.id.textViewAddresseItem);
            ratingBarRate = itemView.findViewById(R.id.ratingBarRate);
        }
    }
}

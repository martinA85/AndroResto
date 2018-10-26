package com.example.mallimonier2017.androresto.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {

    private String name;
    private String address;
    private String lat;
    private String longi;
    private Float rating;
    private String id;

    public Place(){

    }

    public Place(String name, String address, String lat, String longi, Float rating, String id) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.longi = longi;
        this.rating = rating;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLat() {
        return lat;
    }

    public String getLongi() {
        return longi;
    }

    public Float getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected Place(Parcel in) {
        name = in.readString();
        address = in.readString();
        lat = in.readString();
        longi = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readFloat();
        }
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(lat);
        dest.writeString(longi);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(rating);
        }
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}

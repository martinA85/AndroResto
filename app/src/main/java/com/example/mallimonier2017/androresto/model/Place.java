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
    private String dist = "0";

    public Place(){

    }

    public Place(String name, String address, String lat, String longi, Float rating, String id) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.longi = longi;
        this.rating = rating;
        this.id = id;
        this.computeDist(new Float(47.2238986), new Float(-1.6216199));
    }

    public Place(String name, String address, String lat, String longi, Float rating, String id, float latActual, float longiActual) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.longi = longi;
        this.rating = rating;
        this.id = id;
        this.computeDist(new Float(latActual), new Float(longiActual));
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

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public void computeDist(float lat2, float lon2){
        String unit = "K";
        double lat1 = new Double(this.getLat());
        double lon1 = new Double(this.getLongi());
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }
        dist = Math.round(dist);
        String distStr = String.valueOf(dist);
        distStr += " KM";
        this.dist = distStr;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
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

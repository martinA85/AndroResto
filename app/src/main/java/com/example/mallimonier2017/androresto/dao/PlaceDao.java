package com.example.mallimonier2017.androresto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mallimonier2017.androresto.bddHelper.PlaceBddHelper;
import com.example.mallimonier2017.androresto.contract.PlaceContract;
import com.example.mallimonier2017.androresto.model.Place;

import java.util.ArrayList;

public class PlaceDao {
    private SQLiteDatabase db;
    private PlaceBddHelper helper;

    public PlaceDao(Context context)
    {
        helper = new PlaceBddHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Place item){
        ContentValues values = new ContentValues();
        values.put(PlaceContract.COL_NAME, item.getName());
        values.put(PlaceContract.COL_ADDRESS, item.getAddress());
        values.put(PlaceContract.COL_LAT, item.getLat());
        values.put(PlaceContract.COL_LONG, item.getLongi());
        values.put(PlaceContract.COL_ID, item.getId());
        return db.insert(PlaceContract.TABLE_NAME,null,values);
    }

    public ArrayList<Place> selectAll(){
        Cursor cursor = db.rawQuery("select * from PLACE",null);
        ArrayList<Place> list = new ArrayList<Place>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex("NAME"));
                String id = cursor.getString(cursor.getColumnIndex("ID"));
                String lat = cursor.getString(cursor.getColumnIndex("LAT"));
                String longi = cursor.getString(cursor.getColumnIndex("LONG"));
                String addresse = cursor.getString(cursor.getColumnIndex("ADDRESS"));
                Float rate = cursor.getFloat(cursor.getColumnIndex("RATE"));

                list.add(new Place(name, addresse, lat, longi, rate, id));
                cursor.moveToNext();
            }
        }
        return list;
    }
}

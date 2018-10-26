package com.example.mallimonier2017.androresto.bddHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.mallimonier2017.androresto.contract.PlaceContract;

public class PlaceBddHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "AndroResto.db";
    private final static int DATABASE_VERSION = 1;

    public PlaceBddHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlaceContract.SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PlaceContract.SQL_DROP_TABLE);
        onCreate(db);
    }
}

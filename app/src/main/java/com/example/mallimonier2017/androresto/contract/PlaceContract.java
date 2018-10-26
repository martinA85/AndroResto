package com.example.mallimonier2017.androresto.contract;

public abstract class PlaceContract {
    public static final String TABLE_NAME = " PLACE ";
    public static final String COL_ID = " ID ";
    public static final String COL_ADDRESS = " ADDRESS ";
    public static final String COL_LAT = " LAT ";
    public static final String COL_LONG = " LONG ";
    public static final String COL_RATE = " RATE ";
    public static final String COL_NAME = " NAME ";
    public static final String SQL_CREATE_TABLE =
            " CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME + " ("
                    + COL_ID
                    + " TEXT , "
                    + COL_ADDRESS
                    + " TEXT , "
                    + COL_LAT
                    + " TEXT , "
                    + COL_LONG
                    + " TEXT , "
                    + COL_RATE
                    + " REAL , "
                    + COL_NAME
                    + " TEXT )";
    public static final String SQL_DROP_TABLE =
            " DROP TABLE IF EXISTS "
                    + TABLE_NAME;
}

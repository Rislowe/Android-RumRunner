package com.example.rumrunner.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo1.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DRINK_NAME = "strDrink";
    public static final String DRINK_PIC = "strDrinkThumb";
    public static final String ID ="_id";

    public static final String TABLE_NAME = "cocktails";

    private static DatabaseHelper instance = null;

    public static DatabaseHelper getInstance(Context context)
    {
        /*Singleton Pattern
            design pattern that restricts the instantiation of a class to one object. Useful for when
            exactly one object is needed to coordinate actions across the system
        */
        if (instance ==null)
        {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    private DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY," +
                " strDrink TEXT NOT NULL, " +
                " strDrinkThumb TEXT NOT NULL)";

        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

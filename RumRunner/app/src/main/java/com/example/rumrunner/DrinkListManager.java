package com.example.rumrunner;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rumrunner.domain.DatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DrinkListManager {
    private DatabaseHelper dbHelper;
    public DrinkListManager(Context context) {dbHelper=DatabaseHelper.getInstance(context);}

    @SuppressLint("SimpleDateFormat")
    public List<DrinkItem> getItems() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +DatabaseHelper.TABLE_NAME,
                null
        );

        List<DrinkItem> items = new ArrayList<>();


        if(cursor.moveToFirst()) //if db has at least 1 row of data
        {
            while(!cursor.isAfterLast())
            {

                @SuppressLint({"Range", "SimpleDateFormat"}) DrinkItem item = new DrinkItem(
                        cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.DRINK_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.DRINK_PIC)));

                items.add(item);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return items;
    }

    public void addItem(DrinkItem item)
    {
        //New Database code
        //creates a new content value item that can be passed to database
        ContentValues newItem = new ContentValues();
        newItem.put(DatabaseHelper.ID, item.getId());
        newItem.put(DatabaseHelper.DRINK_NAME, item.getStrDrinkName());
        newItem.put(DatabaseHelper.DRINK_PIC, item.getStrDrinkName());

        //gets a writable copy of the sqlite database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //inserts the new item into the database
        db.insert(DatabaseHelper.TABLE_NAME, null, newItem);
    }

    public void removeItem(DrinkItem item)
    {
        //Delete Item
        //Where _id=""

        SQLiteDatabase db = dbHelper.getWritableDatabase(); //setup writable Database
        String[] args = new String[] {String.valueOf(item.getId())}; //set up arguments (id)

        //Delete from database
        db.delete(
                DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ID + "=?",
                args
        );
    }

    public void updateItem(DrinkItem item)
    {
        //UPDATE item
        //SET Description = "", completed = ""
        //WHERE _id = ""

        ContentValues updateItem = new ContentValues();

        updateItem.put(DatabaseHelper.DRINK_NAME, item.getStrDrinkName());
        updateItem.put(DatabaseHelper.DRINK_PIC, item.getStrDrinkThumb());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] args = new String[] {String.valueOf(item.getId())};

        db.update(
                DatabaseHelper.TABLE_NAME,
                updateItem,
                DatabaseHelper.ID + "=?",
                args
        );

    }

}

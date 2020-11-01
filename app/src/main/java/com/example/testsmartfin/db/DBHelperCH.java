package com.example.testsmartfin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testsmartfin.PostCheck;

import java.util.ArrayList;
import java.util.List;

public class DBHelperCH  extends SQLiteOpenHelper {
    static String DB_NAME = "history.db";
    static String CONTACTS_COLUMN_NAME = "name";
    static String CONTACTS_COLUMN_PRICE = "price";
    static String CONTACTS_COLUMN_SIZE = "size";
    static String CONTACTS_COLUMN_NUMBER = "numper";
    static Context context;
    private final List<PostCheck> mPost = new ArrayList<>();
    public DBHelperCH(Context context){
        super(context, DB_NAME,null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,price text,size text,numper integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
    void insertCheck(PostCheck postCheck, int number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, postCheck.getName());
        contentValues.put(CONTACTS_COLUMN_PRICE, Double.toString(postCheck.getPrice()));
        contentValues.put(CONTACTS_COLUMN_SIZE, Double.toString(postCheck.getSize()));
        contentValues.put(CONTACTS_COLUMN_NUMBER, number);
        db.insert("contacts", null, contentValues);

        db.close();
    }

    int getCount() {
        int COUNT=0;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contacts", null);
        cursor.moveToFirst();
        int mol=cursor.getCount();
        if (mol!=0){
            cursor.moveToLast();
            COUNT = cursor.getInt(cursor.getColumnIndex(CONTACTS_COLUMN_NUMBER));}
        cursor.close();
        return COUNT;
    }
}

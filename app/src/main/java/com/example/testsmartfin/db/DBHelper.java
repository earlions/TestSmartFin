package com.example.testsmartfin.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.testsmartfin.Post;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.Throws;

public class DBHelper extends SQLiteOpenHelper {
    static String DB_NAME = "range.db";
    static String DB_PATH;
    static String TABLE = "range";
    // названия столбцов
    static String COLUMN_ID = "_id";
    static String COLUMN_NAME = "name";
    static String COLUMN_PRICE = "price";
    static String COLUMN_IMAGE = "image";
    static String COLUMN_CITY = "city";
    static Context context;
    private final List<Post> mPost = new ArrayList<>();
    public DBHelper(Context context){
        super(context, DB_NAME,null,1);
        DB_PATH = context.getFilesDir().getPath()+DB_NAME;
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    public List<Post> getDataAll() throws SQLException {
        SQLiteDatabase db = open();
        Cursor cursor;
        cursor = db.rawQuery("select * from "+TABLE, null);
        cursor.moveToFirst();
        int counter = cursor.getCount();
        mPost.clear();
        for (int i=0;i<counter;i++){
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE));
            String image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
            Post post = new Post(name,city,Float.parseFloat(price),image);
            mPost.add(post);
            cursor.moveToNext();
        }
        cursor.close();
        return mPost;
    }

    public List<Post> getData(String select) throws SQLException {
        SQLiteDatabase db = open();
        Cursor cursor;
        cursor = db.rawQuery("select * from "+TABLE+ " where city = "+"'"+select+"'", null);
        cursor.moveToFirst();
        int counter = cursor.getCount();
        mPost.clear();
        for (int i=0;i<counter;i++){
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String price = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE));
            String image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
            String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
            Post post = new Post(name,city,Float.parseFloat(price),image);
            mPost.add(post);
            cursor.moveToNext();
        }
        cursor.close();
        return mPost;
    }

    public void cread_DB(){
        InputStream myInput=null;
        OutputStream myOutput=null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()){
                this.getReadableDatabase();
                myInput = context.getAssets().open(DB_NAME);
                String outFileName = DB_PATH;
                myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SQLiteDatabase open() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}

package com.example.testsmartfin.db;

import com.example.testsmartfin.Post;

import java.util.List;

import io.reactivex.Observable;

public interface IRepository {
    Observable<List<Post>> getRead(DBHelper dbHelper,String city);
    Observable<List<Post>> getReadAll(DBHelper dbHelper);
}

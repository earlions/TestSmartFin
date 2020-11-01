package com.example.testsmartfin.db;

import com.example.testsmartfin.Post;

import java.util.List;
import io.reactivex.Observable;
import java.util.*;

public class RepositoryImpl implements IRepository {
    @Override
    public Observable<List<Post>> getRead(DBHelper dbHelper, String city) {
        return Observable.create(observableEmitter ->{
            List<Post> mPost = new ArrayList<>();
            try {
                mPost.addAll(dbHelper.getData(city));
                observableEmitter.onNext(mPost);
            } finally {
                observableEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Post>> getReadAll(DBHelper dbHelper) {
        return Observable.create(observableEmitter ->{
            List<Post> mPost = new ArrayList<>();
            try {
                mPost.addAll(dbHelper.getDataAll());
                observableEmitter.onNext(mPost);
            } finally {
                observableEmitter.onComplete();
            }
        });
    }
}

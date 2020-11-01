package com.example.testsmartfin.db;

import com.example.testsmartfin.Post;
import com.example.testsmartfin.PostCheck;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class RepositoryCheck implements IRepositoryCheck {
    @Override
    public Observable<Integer> getCount(DBHelperCH dbHelperCH) {
        return Observable.create(observableEmitter ->{
            try {
                int number=dbHelperCH.getCount();
                observableEmitter.onNext(number);
            } finally {
                observableEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> insertDataCheck(DBHelperCH dbHelperCH, int number, PostCheck mPost) {
        return Observable.create(observableEmitter ->{
            try {
                dbHelperCH.insertCheck(mPost,number);
                observableEmitter.onNext(true);
            } finally {
                observableEmitter.onComplete();
            }
        });
    }
}

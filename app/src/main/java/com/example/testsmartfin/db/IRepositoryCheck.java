package com.example.testsmartfin.db;

import com.example.testsmartfin.PostCheck;

import io.reactivex.Observable;

public interface IRepositoryCheck {
    Observable<Integer> getCount(DBHelperCH dbHelperCH);
    Observable<Boolean> insertDataCheck( DBHelperCH dbHelperCH,int number, PostCheck mPost);
}

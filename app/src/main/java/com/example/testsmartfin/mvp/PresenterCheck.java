package com.example.testsmartfin.mvp;

import android.content.Context;

import com.example.testsmartfin.Post;
import com.example.testsmartfin.PostCheck;
import com.example.testsmartfin.db.DBHelperCH;
import com.example.testsmartfin.db.RepositoryCheck;
import com.example.testsmartfin.db.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterCheck implements MVPView.PresenterCheck {
    MVPView.ViewCheck viewCheck;
    Context context;
    public PresenterCheck(MVPView.ViewCheck viewCheck, Context context){
        this.viewCheck = viewCheck;
        this.context = context;
    }
    @Override
    public void onLoadData() {
        DBHelperCH dbHelperCH = new DBHelperCH(context);

        new RepositoryCheck().getCount(dbHelperCH)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer posts) {

                        viewCheck.showNumberCheck(posts);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void insertDara(List<PostCheck> posts, int number) {
        DBHelperCH dbHelperCH = new DBHelperCH(context);
        RepositoryCheck repositoryCheck = new RepositoryCheck();
        for (int i=0;i<posts.size();i++)
            repositoryCheck.insertDataCheck(dbHelperCH,number,posts.get(i))
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Boolean posts) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}

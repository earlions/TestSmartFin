package com.example.testsmartfin.mvp;

import android.content.Context;
import android.widget.Switch;

import com.example.testsmartfin.Post;
import com.example.testsmartfin.db.DBHelper;
import com.example.testsmartfin.db.RepositoryImpl;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements MVPView.Presenter {
    private MVPView.View view;
    private Context context;
    public Presenter(MVPView.View view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void onLoadData(int city) {
        switch(city){
            case 0:
                onLoadDataAll();
                break;
            case 1: onLoadDataSet("Сочи");
                break;
            case 2:
                onLoadDataSet("Россия");
                break;
            case 3: onLoadDataSet("Белорусь");
                break;
        }
    }

    void onLoadDataAll(){
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.cread_DB();

        new RepositoryImpl().getReadAll(dbHelper)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Post> posts) {
                        view.showData(posts);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void onLoadDataSet(String city){
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.cread_DB();

        new RepositoryImpl().getRead(dbHelper,city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Post> posts) {
                        view.showData(posts);
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

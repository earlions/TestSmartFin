package com.example.testsmartfin.mvp;

import com.example.testsmartfin.Post;
import com.example.testsmartfin.PostCheck;

import java.util.ArrayList;
import java.util.List;

public interface MVPView {
    interface View{
        void showData(List<Post> posts);
        void showComplete();

    }
    interface Presenter{
        void onLoadData(int city);
    }
    interface ViewCheck{
        void showNumberCheck(int number);
    }
    interface PresenterCheck{
        void onLoadData();
        void insertDara(List<PostCheck> posts, int number);
    }
}

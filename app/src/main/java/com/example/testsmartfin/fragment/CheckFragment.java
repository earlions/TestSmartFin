package com.example.testsmartfin.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testsmartfin.Post;
import com.example.testsmartfin.PostCheck;
import com.example.testsmartfin.R;
import com.example.testsmartfin.adapter.RecyclerChechAdapter;
import com.example.testsmartfin.mvp.MVPView;
import com.example.testsmartfin.mvp.PresenterCheck;

import java.util.ArrayList;
import java.util.List;

public class CheckFragment extends Fragment implements MVPView.ViewCheck {
    private final List<PostCheck> mPosts = new ArrayList<>();
    private int number = 0;
    private double summa = 0.00;
    private RecyclerView recyclerView;
    private  Button buttonClear;
    private   Button buttonCheck;
    private  TextView textViewSum;
    private   TextView textViewNumberCheck;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        buttonClear = (Button) view.findViewById(R.id.buttonClearCheck);
        buttonCheck = (Button) view.findViewById(R.id.buttonCheck);
        textViewNumberCheck = (TextView) view.findViewById(R.id.tvNumberCheck);
        textViewSum = (TextView) view.findViewById(R.id.textViewSum);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerChechAdapter(mPosts));
        PresenterCheck presenterCheck = new PresenterCheck(this,getContext());
        presenterCheck.onLoadData();
        buttonClear.setOnClickListener(v->{
            mPosts.clear();
            recyclerView.getAdapter().notifyDataSetChanged();
            buttonCheck.setBackgroundColor(Color.WHITE);
            buttonCheck.setTextColor(getResources().getColor(R.color.grey));

        });
        buttonCheck.setOnClickListener(v->{
            presenterCheck.insertDara(mPosts,number);
            mPosts.clear();
            recyclerView.getAdapter().notifyDataSetChanged();
            buttonCheck.setBackgroundColor(Color.WHITE);
            buttonCheck.setTextColor(getResources().getColor(R.color.grey));
            textViewNumberCheck.setText("#"+(++number));
        });

        return view;
    }

    @Override
    public void showNumberCheck(int numb) {
        number=numb+1;
        textViewNumberCheck.setText("#"+number);

    }
    public void setText(String name, double price, double size){
        PostCheck post = new PostCheck(name,price,size);
        int mat = (int)(price*size)*100;
        summa+=(double) mat/100;
        mPosts.add(post);
        recyclerView.getAdapter().notifyDataSetChanged();
        buttonCheck.setBackgroundColor(getResources().getColor(R.color.teal_200));
        buttonCheck.setTextColor(Color.BLACK);
        textViewSum.setText(String.valueOf(summa));
    }
}
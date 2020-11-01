package com.example.testsmartfin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testsmartfin.Post;
import com.example.testsmartfin.R;
import com.example.testsmartfin.adapter.OnFragmentClick;
import com.example.testsmartfin.adapter.RecyclerRestAdapter;
import com.example.testsmartfin.mvp.MVPView;
import com.example.testsmartfin.mvp.Presenter;

import java.util.ArrayList;
import java.util.List;

public class RangeFragment extends Fragment implements MVPView.View, OnFragmentClick {
    private OnFragmentInteractionListener mListener;
    private final List<Post> mPost = new ArrayList<>();
    private RecyclerView recyclerView;

    public static RangeFragment newInstance(int position){
        Bundle args = new Bundle();
        args.putInt("ps", position);
        RangeFragment fragment = new RangeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_range, container, false);
        assert getArguments() != null;
        int position = getArguments().getInt("ps", 0);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        recyclerView.setAdapter(new RecyclerRestAdapter(mPost,this));
        Presenter presenter = new Presenter(this,getContext());
        presenter.onLoadData(position);
        return view;
    }

    @Override
    public void showData(List<Post> posts) {
        mPost.clear();
        mPost.addAll(posts);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void onFragmentClick(String name, String price) {
        mListener.onFragmentInteraction(name,price);
    }

   public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String name, String price);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener)context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

}
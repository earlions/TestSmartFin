package com.example.testsmartfin.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testsmartfin.PostCheck;
import com.example.testsmartfin.R;
import java.util.ArrayList;
import java.util.List;

public class RecyclerChechAdapter extends RecyclerView.Adapter<RecyclerChechAdapter.TestHolder>{
    private List<PostCheck> mPosts = new ArrayList<>();
    public RecyclerChechAdapter(List<PostCheck> mPosts){
        this.mPosts = mPosts;
    }
    @NonNull
    @Override
    public RecyclerChechAdapter.TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_check,parent,false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerChechAdapter.TestHolder holder, int position) {
        PostCheck post = mPosts.get(position);
        holder.bindCrime(post);

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    static class TestHolder extends RecyclerView.ViewHolder{
        TextView textViewHome;
        TextView textViewOnePrice;
        TextView textViewSum;
        TextView textViewPrice;
        TestHolder(View itemView){
            super(itemView);
            textViewHome = itemView.findViewById(R.id.textView_home);
            textViewOnePrice = itemView.findViewById(R.id.textViewOnePrice);
            textViewSum = itemView.findViewById(R.id.textViewPriceSum);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
        @SuppressLint("SetTextI18n")
        void bindCrime(PostCheck mPost) {
            textViewHome.setText(mPost.getName());
            textViewOnePrice.setText(String.valueOf(mPost.getPrice()));
            textViewSum.setText(String.valueOf(mPost.getSize())+"x"+ String.valueOf(mPost.getPrice()));
            int sum = (int)(mPost.getSize()*mPost.getPrice()*100);
            textViewPrice.setText(String.valueOf((double) sum/100.00));
        }
    }


}

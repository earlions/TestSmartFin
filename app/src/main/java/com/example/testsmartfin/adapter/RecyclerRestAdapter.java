package com.example.testsmartfin.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testsmartfin.Post;
import com.example.testsmartfin.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
//
public class RecyclerRestAdapter extends RecyclerView.Adapter<RecyclerRestAdapter.TestHolder>   {
    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_home,parent,false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Post post = mPosts.get(position);
        holder.bindCrime(post);
        holder.relativeLayout.setOnClickListener(v -> {
                view.onFragmentClick(post.getName(), String.valueOf(post.getPrice()));
        });
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    class TestHolder extends RecyclerView.ViewHolder{
        TextView textViewHome;
        CardView cardView;
        ImageView imageView;
        RelativeLayout relativeLayout;
    TestHolder(View itemView){
        super(itemView);
        textViewHome = itemView.findViewById(R.id.textView_home);
        cardView = itemView.findViewById(R.id.card_view);
        imageView = itemView.findViewById(R.id.image_crime);
        relativeLayout = itemView.findViewById(R.id.rl_sitt);
    }
    void bindCrime(Post mPost) {

        textViewHome.append(mPost.getName());
        Picasso.get().load(mPost.getImage()).fit().centerCrop().into(imageView);

        switch(mPost.getCity()){
            case "Сочи": cardView.setBackgroundColor(Color.BLUE);
                break;
            case "Россия":cardView.setBackgroundColor(Color.CYAN);
            break;
            case "Белорусь": cardView.setBackgroundColor(Color.YELLOW);
            break;
        }

    }
}
    private List<Post> mPosts = new ArrayList<>();
    private OnFragmentClick view;
    public RecyclerRestAdapter(List<Post> mPosts, OnFragmentClick view){
        this.mPosts = mPosts;
        this.view = view;
    }


}

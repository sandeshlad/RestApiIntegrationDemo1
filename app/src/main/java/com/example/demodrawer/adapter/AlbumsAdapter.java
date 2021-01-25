package com.example.demodrawer.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demodrawer.R;
import com.example.demodrawer.model.Albums;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>{

    private Context context;
    private List<Albums> albumsList;

    public AlbumsAdapter(Context context, List<Albums> albums) {
        this.context = context;
        this.albumsList = albums;
    }


        @NonNull
        @Override
        public AlbumsViewHolder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item_layout, parent, false);

            return new AlbumsViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder (@NonNull AlbumsViewHolder holder,int position){
            holder.id.setText(Integer.toString(albumsList.get(position).getId()));
            holder.userId.setText(Integer.toString(albumsList.get(position).getUserId()));
            holder.title.setText(albumsList.get(position).getTitle());

        }

        @Override
        public int getItemCount () {
            return albumsList.size();
        }

        public static class AlbumsViewHolder extends RecyclerView.ViewHolder {

            TextView id;
            TextView userId;
            TextView title;


            public AlbumsViewHolder(@NonNull View itemView) {
                super(itemView);
                userId= itemView.findViewById(R.id.tvUserId);
                id = itemView.findViewById(R.id.tvId);
                title = itemView.findViewById(R.id.tvTitle);
            }


        }

}






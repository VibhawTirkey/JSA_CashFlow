package com.jsa.analytics.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.databinding.ItemHomeVideoBinding;

public class HomeVideoAdapter extends RecyclerView.Adapter<HomeVideoAdapter.VideoViewHolder> {
    Context context;
    String id = "DjokXL8ZHSQ";

    public HomeVideoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeVideoBinding binding = ItemHomeVideoBinding.inflate(LayoutInflater.from(context),parent,false);
        return new VideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
                appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                webIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        ItemHomeVideoBinding binding;

        public VideoViewHolder(ItemHomeVideoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

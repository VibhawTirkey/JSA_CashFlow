package com.jsa.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.databinding.ItemHomeVideoBinding;

public class HomeVideoAdapter extends RecyclerView.Adapter<HomeVideoAdapter.VideoViewHolder> {
    Context context;

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

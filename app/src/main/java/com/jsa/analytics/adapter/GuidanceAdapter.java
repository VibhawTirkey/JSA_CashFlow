package com.jsa.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.databinding.ItemGuidanceBinding;
import com.jsa.analytics.model.GuidanceModel;

import java.util.List;

public class GuidanceAdapter extends RecyclerView.Adapter<GuidanceAdapter.GuidanceViewHolder> {
    Context context;
    List<GuidanceModel> list;

    public GuidanceAdapter(Context context, List<GuidanceModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GuidanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGuidanceBinding binding = ItemGuidanceBinding.inflate(LayoutInflater.from(context),parent,false);
        return new GuidanceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GuidanceViewHolder holder, int position) {
        GuidanceModel model = list.get(position);
        holder.binding.title.setText(model.getTitle());
        holder.binding.description.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GuidanceViewHolder extends RecyclerView.ViewHolder {
        ItemGuidanceBinding binding;
        public GuidanceViewHolder(ItemGuidanceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

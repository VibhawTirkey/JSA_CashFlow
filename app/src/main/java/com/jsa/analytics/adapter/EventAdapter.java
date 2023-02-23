package com.jsa.analytics.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.databinding.ItemEventBinding;
import com.jsa.analytics.ui.EventDetailActivity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    Context context;

    public EventAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEventBinding binding = ItemEventBinding.inflate(LayoutInflater.from(context),parent,false);
        return new EventViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        ItemEventBinding binding;
        public EventViewHolder(ItemEventBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

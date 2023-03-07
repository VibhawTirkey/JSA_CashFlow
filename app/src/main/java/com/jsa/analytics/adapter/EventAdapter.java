package com.jsa.analytics.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jsa.analytics.databinding.ItemEventBinding;
import com.jsa.analytics.model.EventModel;
import com.jsa.analytics.ui.EventDetailActivity;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    Context context;
    List<EventModel> list;

    public EventAdapter(Context context, List<EventModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEventBinding binding = ItemEventBinding.inflate(LayoutInflater.from(context),parent,false);
        return new EventViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventModel data = list.get(position);
        holder.binding.title.setText(data.getEventName());
        holder.binding.location.setText(data.getEventLocation());
        Glide.with(context).load(data.getEventImage()).into(holder.binding.eventImg);
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, EventDetailActivity.class);
            intent.putExtra("eventName",data.getEventName());
            intent.putExtra("eventDate",data.getEventDate());
            intent.putExtra("eventDetail",data.getEventDetail());
            intent.putExtra("eventLocation",data.getEventLocation());
            intent.putExtra("eventImage",data.getEventImage());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        ItemEventBinding binding;
        public EventViewHolder(ItemEventBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

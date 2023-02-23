package com.jsa.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.databinding.ItemTestimonialBinding;

public class HomeTestimonialAdapter extends RecyclerView.Adapter<HomeTestimonialAdapter.TestimonialViewHolder> {

    Context context;

    public HomeTestimonialAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TestimonialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTestimonialBinding binding = ItemTestimonialBinding.inflate(LayoutInflater.from(context),parent,false);
        return new TestimonialViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TestimonialViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class TestimonialViewHolder extends RecyclerView.ViewHolder {
        ItemTestimonialBinding binding;
        public TestimonialViewHolder(ItemTestimonialBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

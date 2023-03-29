package com.jsa.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jsa.analytics.databinding.ItemTestimonialBinding;
import com.jsa.analytics.model.TestimonialModel;

import java.util.List;

public class HomeTestimonialAdapter extends RecyclerView.Adapter<HomeTestimonialAdapter.TestimonialViewHolder> {

    Context context;
    List<TestimonialModel> list;

    public HomeTestimonialAdapter(Context context, List<TestimonialModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TestimonialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTestimonialBinding binding = ItemTestimonialBinding.inflate(LayoutInflater.from(context),parent,false);
        return new TestimonialViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TestimonialViewHolder holder, int position) {
        TestimonialModel data = list.get(position);
        Glide.with(context).load(data.getProfile_img()).centerCrop()
                .into(holder.binding.image);
        holder.binding.name.setText(data.getName());
        holder.binding.desigCompany.setText(data.getDesignation()+", "+data.getCompany());
        holder.binding.description.setText(data.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TestimonialViewHolder extends RecyclerView.ViewHolder {
        ItemTestimonialBinding binding;
        public TestimonialViewHolder(ItemTestimonialBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

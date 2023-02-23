package com.jsa.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ItemHelpBinding;
import com.jsa.analytics.model.HelpModel;

import java.util.List;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.HelpViewHolder> {
    Context context;
    List<HelpModel> helpModelList;

    public HelpAdapter(Context context, List<HelpModel> helpModelList) {
        this.context = context;
        this.helpModelList = helpModelList;
    }

    @NonNull
    @Override
    public HelpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHelpBinding binding = ItemHelpBinding.inflate(LayoutInflater.from(context),parent,false);
        return new HelpViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpViewHolder holder, int position) {
        HelpModel help = helpModelList.get(position);
        holder.binding.title.setText(help.getTitle());
        holder.binding.description.setText(help.getDescription());
        holder.binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.binding.description.getVisibility() == View.VISIBLE){
                    TransitionManager.beginDelayedTransition(holder.binding.getRoot(), new AutoTransition());
                    holder.binding.description.setVisibility(View.GONE);
                    holder.binding.indicator.setImageResource(R.drawable.round_arrow_forward);
                }else {
                    TransitionManager.beginDelayedTransition(holder.binding.getRoot(), new AutoTransition());
                    holder.binding.description.setVisibility(View.VISIBLE);
                    holder.binding.indicator.setImageResource(R.drawable.round_add);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return helpModelList.size();
    }

    public class HelpViewHolder extends RecyclerView.ViewHolder {
        ItemHelpBinding binding;
        public HelpViewHolder(ItemHelpBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

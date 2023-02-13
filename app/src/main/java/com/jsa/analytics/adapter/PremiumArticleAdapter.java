package com.jsa.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.databinding.ItemPremiumArticleBinding;

public class PremiumArticleAdapter extends RecyclerView.Adapter<PremiumArticleAdapter.ArticleViewHolder> {

    private Context context;

    public PremiumArticleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPremiumArticleBinding binding = ItemPremiumArticleBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        ItemPremiumArticleBinding binding;

        public ArticleViewHolder(ItemPremiumArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

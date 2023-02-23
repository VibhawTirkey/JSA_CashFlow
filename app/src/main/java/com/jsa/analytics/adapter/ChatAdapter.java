package com.jsa.analytics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.databinding.ItemReceiveBinding;
import com.jsa.analytics.databinding.ItemSentBinding;
import com.jsa.analytics.model.MessageModel;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {

    Context context;
    List<MessageModel> list;
    private int SENDER = 0;
    private int RECEIVER = 1;

    public ChatAdapter(Context context, List<MessageModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getAdmin()){
            return RECEIVER;
        }else {
            return SENDER;
        }
//        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER){
            ItemSentBinding sentBinding = ItemSentBinding.inflate(LayoutInflater.from(context),parent,false);
            return new SenderViewHolder(sentBinding);
        }else if (viewType == RECEIVER){
            ItemReceiveBinding receiveBinding = ItemReceiveBinding.inflate(LayoutInflater.from(context),parent,false);
            return new ReceiverViewHolder(receiveBinding);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel message = list.get(position);
        if (message.getAdmin()){
            ReceiverViewHolder viewHolder = (ReceiverViewHolder) holder;
            viewHolder.binding.message.setText(message.getMessage());
            viewHolder.binding.time.setText(message.getDate_time());
        }else {
            SenderViewHolder viewHolder = (SenderViewHolder) holder;
            viewHolder.binding.message.setText(message.getMessage());
            viewHolder.binding.time.setText(message.getDate_time());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {
        ItemSentBinding binding;
        public SenderViewHolder(ItemSentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        ItemReceiveBinding binding;
        public ReceiverViewHolder(ItemReceiveBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

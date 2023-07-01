package com.jsa.analytics.adapter;

import static com.jsa.analytics.utils.Constants.CHAT_NODE_STATUS_CLOSED;
import static com.jsa.analytics.utils.Constants.CHAT_NODE_STATUS_ONGOING;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityAskYourQuestionBinding;
import com.jsa.analytics.databinding.ItemAskYourQuestionBinding;
import com.jsa.analytics.model.AskYourQuestionModel;
import com.jsa.analytics.ui.ChatActivity;

import java.util.List;

public class AskYourQuestionAdapter extends RecyclerView.Adapter<AskYourQuestionAdapter.QuestionViewHolder> {
    Context context;
    List<AskYourQuestionModel> list;

    public AskYourQuestionAdapter(Context context, List<AskYourQuestionModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAskYourQuestionBinding binding = ItemAskYourQuestionBinding.inflate(LayoutInflater.from(context),parent,false);
        return new QuestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        AskYourQuestionModel model = list.get(position);
        holder.binding.title.setText(model.getTopic());
        holder.binding.description.setText(model.getDescription());
        if (model.getStatus().equals(CHAT_NODE_STATUS_ONGOING)){
            holder.binding.status.setText("On Progress");
            holder.binding.status.setTextColor(context.getResources().getColor(R.color.a_y_q_text_gray));
            holder.binding.status.setBackgroundColor(context.getResources().getColor(R.color.a_y_q_gray));
        }else if (model.getStatus().equals("view")){
            holder.binding.status.setText("View");
            holder.binding.status.setTextColor(context.getResources().getColor(R.color.a_y_q_text_yellow));
            holder.binding.status.setBackgroundColor(context.getResources().getColor(R.color.a_y_q_yellow));
        } else if (model.getStatus().equals(CHAT_NODE_STATUS_CLOSED)) {
            holder.binding.status.setText("Closed");
            holder.binding.status.setTextColor(context.getResources().getColor(R.color.a_y_q_text_red));
            holder.binding.status.setBackgroundColor(context.getResources().getColor(R.color.a_y_q_red));
        }
        else if (model.getStatus().equals("done")) {
            holder.binding.status.setText("Done");
            holder.binding.status.setTextColor(context.getResources().getColor(R.color.a_y_q_text_green));
            holder.binding.status.setBackgroundColor(context.getResources().getColor(R.color.a_y_q_green));
        }
        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context,ChatActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("title",model.getTopic());
            intent.putExtra("documentId",model.getDocumentId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        ItemAskYourQuestionBinding binding;
        public QuestionViewHolder(ItemAskYourQuestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

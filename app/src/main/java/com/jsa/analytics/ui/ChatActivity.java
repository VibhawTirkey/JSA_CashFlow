package com.jsa.analytics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.jsa.analytics.adapter.ChatAdapter;
import com.jsa.analytics.databinding.ActivityChatBinding;
import com.jsa.analytics.model.MessageModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;
    ChatAdapter adapter;
    List<MessageModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        list = new ArrayList<>();
        list.add(new MessageModel("Hi User!","20 Feb 2023 12:33 am",true));
        list.add(new MessageModel("How can I assist you?","20 Feb 2023 12:34 am",true));
        list.add(new MessageModel("Loss in Net Profit?","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Hi User! Please wait while we check","20 Feb 2023 12:35 am",true));
        list.add(new MessageModel("Thank You for the response.","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Thank You for the Patience.","20 Feb 2023 12:36 am",true));
        list.add(new MessageModel("Hi User!","20 Feb 2023 12:33 am",true));
        list.add(new MessageModel("How can I assist you?","20 Feb 2023 12:34 am",true));
        list.add(new MessageModel("Loss in Net Profit?","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Hi User! Please wait while we check","20 Feb 2023 12:35 am",true));
        list.add(new MessageModel("Thank You for the response.","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Thank You for the Patience.","20 Feb 2023 12:36 am",true));
        list.add(new MessageModel("Hi User!","20 Feb 2023 12:33 am",true));
        list.add(new MessageModel("How can I assist you?","20 Feb 2023 12:34 am",true));
        list.add(new MessageModel("Loss in Net Profit?","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Hi User! Please wait while we check","20 Feb 2023 12:35 am",true));
        list.add(new MessageModel("Thank You for the response.","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Thank You for the Patience.","20 Feb 2023 12:36 am",true));
        list.add(new MessageModel("Hi User!","20 Feb 2023 12:33 am",true));
        list.add(new MessageModel("How can I assist you?","20 Feb 2023 12:34 am",true));
        list.add(new MessageModel("Loss in Net Profit?","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Hi User! Please wait while we check","20 Feb 2023 12:35 am",true));
        list.add(new MessageModel("Thank You for the response.","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Thank You for the Patience.","20 Feb 2023 12:36 am",true));
        list.add(new MessageModel("Hi User!","20 Feb 2023 12:33 am",true));
        list.add(new MessageModel("How can I assist you?","20 Feb 2023 12:34 am",true));
        list.add(new MessageModel("Loss in Net Profit?","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Hi User! Please wait while we check","20 Feb 2023 12:35 am",true));
        list.add(new MessageModel("Thank You for the response.","20 Feb 2023 12:35 am",false));
        list.add(new MessageModel("Thank You for the Patience.","20 Feb 2023 12:36 am",true));
        adapter = new ChatAdapter(getApplicationContext(),list);
        binding.messageList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.messageList.setAdapter(adapter);
        binding.messageList.scrollToPosition(adapter.getItemCount()-1);

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.message.getText().toString().isEmpty()){
                    binding.message.setError("Empty");
                }else {
                    Date date = new Date();
                    String fDate = new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(date);
                    list.add(new MessageModel(binding.message.getText().toString(),fDate,false));
                    binding.messageList.getAdapter().notifyDataSetChanged();
                    binding.messageList.smoothScrollToPosition(adapter.getItemCount()-1);
                    binding.message.setText("");
                }
            }
        });
    }
}
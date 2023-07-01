package com.jsa.analytics.ui;

import static com.jsa.analytics.utils.Constants.CHAT_NODE_STATUS_CLOSED;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.jsa.analytics.adapter.ChatAdapter;
import com.jsa.analytics.databinding.ActivityChatBinding;
import com.jsa.analytics.model.ChatModel;
import com.jsa.analytics.model.MessageModel;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;
    ChatAdapter adapter;
    List<MessageModel> list;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        String documentId = getIntent().getStringExtra("documentId");
        String title = getIntent().getStringExtra("title");
        binding.toolbar.setTitle(title);
        getPreviousChats(documentId);

        list = new ArrayList<>();

        binding.send.setOnClickListener(v -> {
            if (binding.message.getText().toString().isEmpty()){
                binding.message.setError("Empty");
            }else {
                sendMessage(documentId,binding.message.getText().toString());
            }
        });
    }

    private void sendMessage(String documentId,String message) {
        MessageModel messageModel = new MessageModel(message, Timestamp.now(),false,"0");
        firestore.collection("askYourQuestions").document(firebaseAuth.getUid()).collection("questions").document(documentId).update("chats", FieldValue.arrayUnion(messageModel)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    binding.message.setText("");
                }
            }
        });
    }

    private void getPreviousChats(String documentId) {
        firestore.collection("askYourQuestions").document(firebaseAuth.getUid()).collection("questions").document(documentId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value!=null && value.exists()){
                    ChatModel model = value.toObject(ChatModel.class);
                    if (model.getStatus().matches(CHAT_NODE_STATUS_CLOSED)){
                        binding.message.setVisibility(View.GONE);
                        binding.send.setVisibility(View.GONE);
                    }else {
                        binding.message.setVisibility(View.VISIBLE);
                        binding.send.setVisibility(View.VISIBLE);
                    }
                    adapter = new ChatAdapter(getApplicationContext(),model.getChats());
                    binding.messageList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                    if (model.getChats()!=null){
                        binding.messageList.setAdapter(adapter);
                        binding.messageList.scrollToPosition(adapter.getItemCount()-1);
                    }else {
                        Toast.makeText(ChatActivity.this, "Start Chat here", Toast.LENGTH_SHORT).show();
                    }
                    
                }
            }
        });
    }
}
package com.jsa.analytics.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jsa.analytics.adapter.AskYourQuestionAdapter;
import com.jsa.analytics.databinding.ActivityAskYourQuestionBinding;
import com.jsa.analytics.databinding.DialogAddQuestionBinding;
import com.jsa.analytics.model.AskYourQuestionModel;
import com.jsa.analytics.model.PostQuestionModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AskYourQuestionActivity extends AppCompatActivity {

    ActivityAskYourQuestionBinding binding;
    AskYourQuestionAdapter adapter;

    List<AskYourQuestionModel> oldList;
    List<AskYourQuestionModel> newList;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAskYourQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        getAllQuestions();

        binding.addQuestion.setOnClickListener(v -> {
            DialogAddQuestionBinding dialogBinding = DialogAddQuestionBinding.inflate(getLayoutInflater());
            BottomSheetDialog bottomSheet = new BottomSheetDialog(AskYourQuestionActivity.this);
            bottomSheet.setContentView(dialogBinding.getRoot());
//            bottomSheet.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomSheet.show();

            dialogBinding.close.setOnClickListener(v1 -> bottomSheet.dismiss());
            dialogBinding.postQuestion.setOnClickListener(v12 -> {
                String title = dialogBinding.title.getText().toString();
                String description = dialogBinding.description.getText().toString();
                if (!title.isEmpty() || !description.isEmpty()) {
                    postQuestion(title, description);
                    bottomSheet.dismiss();
                }

            });
        });
    }

    private void getAllQuestions() {
        firestore.collection("askYourQuestions").document(firebaseAuth.getUid()).collection("questions").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<AskYourQuestionModel> list = new ArrayList<>();
                for (QueryDocumentSnapshot document:task.getResult()){
                    AskYourQuestionModel askYourQuestionModel = document.toObject(AskYourQuestionModel.class);
                    askYourQuestionModel.setDocumentId(document.getId());
                    list.add(askYourQuestionModel);
                    adapter = new AskYourQuestionAdapter(getApplicationContext(), list);
                    binding.questionList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                    binding.questionList.setAdapter(adapter);
                }
            }else {
                Log.e("TAG", "onComplete: ", task.getException());
            }
        });
    }

    private void postQuestion(String title, String description) {
        Date date = new Date();
        String dateN = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
        PostQuestionModel model = new PostQuestionModel(title, description, "ongoing", new Timestamp(date));
        firestore.collection("askYourQuestions").document(firebaseAuth.getUid()).collection("questions").document(dateN).set(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    getAllQuestions();
                    Toast.makeText(AskYourQuestionActivity.this, "Successfully Submitted Your Query!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
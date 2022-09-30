package com.jsa.analytics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jsa.analytics.databinding.ActivityAnalyticsOutputBinding;
import com.jsa.analytics.model.InputModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnalyticsOutputActivity extends AppCompatActivity {

    ActivityAnalyticsOutputBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    InputModel inputModel;
    float gp,gpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnalyticsOutputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        String date = new SimpleDateFormat("yyyyMM").format(new Date());
        inputModel = new InputModel();
        firebaseFirestore.collection("analyticsData").document(firebaseAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
//                    inputModel = documentSnapshot.get(date,InputModel.class);
                    setDataResult();
//                    inputModel = documentSnapshot.toObject(InputModel.class);
                }else {
                    Toast.makeText(AnalyticsOutputActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setDataResult() {
        binding.planGpp.setText(String.valueOf(inputModel.getFinancialSummary().getGrossProfitRatio().getPlan().getData()));
        binding.actualGpp.setText(String.valueOf(inputModel.getFinancialSummary().getGrossProfitRatio().getActual().getData()));
        binding.planNpp.setText(String.valueOf(inputModel.getFinancialSummary().getNetProfitRatio().getPlan().getData()));
        binding.actualNpp.setText(String.valueOf(inputModel.getFinancialSummary().getNetProfitRatio().getActual().getData()));
        binding.planRoi.setText(String.valueOf(inputModel.getFinancialSummary().getNetProfitRatio().getActual().getData()));
        binding.actualRoi.setText(String.valueOf(inputModel.getFinancialSummary().getNetProfitRatio().getActual().getData()));
    }
}
package com.jsa.analytics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityForgetPasswordBinding;

public class ForgetPasswordActivity extends AppCompatActivity {

    ActivityForgetPasswordBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        binding.back.setOnClickListener(view -> onBackPressed());

        binding.submit.setOnClickListener(view -> {
            String email = binding.email.getText().toString();
            binding.status.setVisibility(View.GONE);
            if (!email.isEmpty()){
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            binding.email.setText("");
                            binding.status.setVisibility(View.VISIBLE);
                            binding.status.setText("Email sent to: "+email+"\nKindly check for the mail in your Email Account");
                            Toast.makeText(ForgetPasswordActivity.this, "Email sent to: "+email, Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ForgetPasswordActivity.this, "Error Sending to: "+email, Toast.LENGTH_SHORT).show();
                            binding.status.setText("Error Sending to: "+email+"\n"+task.getException().getMessage());
                            binding.status.setVisibility(View.VISIBLE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.status.setText(e.toString());
                        binding.status.setVisibility(View.VISIBLE);
                    }
                });
            }else {
                binding.email.setError("Enter your Email");
            }
        });
    }
}
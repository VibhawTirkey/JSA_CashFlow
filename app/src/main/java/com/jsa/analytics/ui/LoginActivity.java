package com.jsa.analytics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jsa.analytics.databinding.ActivityLoginBinding;
import com.jsa.analytics.utils.Utilities;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        if (mUser != null){
            startActivity(new Intent(getApplicationContext(),HomeScreenActivity.class));
            finish();
        }

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
                finish();
            }
        });

        binding.login.setOnClickListener(view -> {
            if (!binding.username.getText().toString().isEmpty()){
                if (!binding.password.getText().toString().isEmpty()){
                    if (Utilities.isValidEmail(binding.username.getText().toString())){
                        loginUser(binding.username.getText().toString(),binding.password.getText().toString());
                    }else {
                        binding.userContainer.setHelperText("Invalid Username");
                    }
                }else {
                    binding.passContainer.setHelperText("Empty");
                }
            }else {
                binding.userContainer.setHelperText("Empty");
            }
            if (Utilities.isValidEmail(binding.username.getText().toString())){

            }

        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(),HomeScreenActivity.class));
                    finish();
                }else {
                    binding.passContainer.setHelperText(task.toString());
                    binding.passContainer.setErrorEnabled(true);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        startActivity(new Intent(getApplicationContext(),OnBoardingActivity.class));
//        finish();
    }
}
package com.jsa.analytics.ui;

import static com.jsa.analytics.utils.Constants.DATE_FORMAT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jsa.analytics.databinding.ActivitySignupBinding;
import com.jsa.analytics.model.SignupUserModel;
import com.jsa.analytics.utils.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.fullName.getText().toString().isEmpty()){
                    if (!binding.email.getText().toString().isEmpty()){
                        if (!binding.mobileNumber.getText().toString().isEmpty()){
                            if (!binding.password.getText().toString().isEmpty()){
                                if (!binding.confirmPassword.getText().toString().isEmpty()){
                                    if (Utilities.isValidEmail(binding.email.getText().toString())){
                                        if (binding.password.getText().toString().matches(binding.confirmPassword.getText().toString())){
                                            signupUser(binding.email.getText().toString(),binding.password.getText().toString());
                                        }else {
                                            binding.confirmPassContainer.setError("Not Matched");
                                        }
                                    }else {
                                        binding.emailContainer.setError("Incorrect Email address");
                                    }
                                }else {
                                    binding.confirmPassContainer.setError("Empty");
                                }
                            }else {
                                binding.passContainer.setError("Empty");
                            }
                        }else {
                            binding.mobileContainer.setError("Empty");
                        }
                    }else {
                        binding.emailContainer.setError("Empty");
                    }
                }else {
                    binding.nameContainer.setError("Empty");
                }
            }
        });
    }

    private void signupUser(String email, String password) {

        SignupUserModel signupUserModel = new SignupUserModel();
        signupUserModel.setCreatedDate(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
        signupUserModel.setEmail(email);
        signupUserModel.setExclusiveMode("none");
        signupUserModel.setExclusiveMember(false);
        signupUserModel.setName(binding.fullName.getText().toString());
        signupUserModel.setPhone(binding.mobileNumber.getText().toString());

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    database.collection("users").document(firebaseAuth.getUid())
                                    .set(signupUserModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this, "Successfully Signed up", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                                        finish();
                                    }else {
                                        Toast.makeText(SignupActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {
                    Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
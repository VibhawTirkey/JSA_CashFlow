package com.jsa.analytics.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.jsa.analytics.databinding.DialogLogoutBinding;
import com.jsa.analytics.databinding.FragmentProfileBinding;
import com.jsa.analytics.ui.LoginActivity;
import com.jsa.analytics.ui.PrivacyPolicyActivity;
import com.jsa.analytics.ui.SecurityActivity;
import com.jsa.analytics.ui.TermAndConditionActivity;

import java.util.Objects;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    Dialog dialog;
    DialogLogoutBinding logoutBinding;
    FirebaseAuth firebaseAuth;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);

        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new Dialog(getContext());
        logoutBinding = DialogLogoutBinding.inflate(inflater);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(logoutBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        binding.termAndConditions.setOnClickListener(v -> startActivity(new Intent(getContext(), TermAndConditionActivity.class)));
        binding.security.setOnClickListener(v -> startActivity(new Intent(getContext(), SecurityActivity.class)));
        binding.privacyPolicy.setOnClickListener(v -> startActivity(new Intent(getContext(), PrivacyPolicyActivity.class)));
        binding.logout.setOnClickListener(v -> dialog.show());
        logoutBinding.yes.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));
            requireActivity().finish();
        });
        logoutBinding.no.setOnClickListener(v -> dialog.dismiss());

        
        return binding.getRoot();
    }
}
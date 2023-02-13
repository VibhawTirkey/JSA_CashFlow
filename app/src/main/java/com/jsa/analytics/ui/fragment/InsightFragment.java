package com.jsa.analytics.ui.fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.jsa.analytics.R;
import com.jsa.analytics.adapter.InsightAdapter;
import com.jsa.analytics.databinding.FragmentInsightBinding;

public class InsightFragment extends Fragment {

    FragmentInsightBinding binding;

    public InsightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInsightBinding.inflate(inflater,container,false);

        InsightAdapter adapter = new InsightAdapter(getFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        return binding.getRoot();
    }
}
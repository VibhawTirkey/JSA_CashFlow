package com.jsa.analytics.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsa.analytics.R;
import com.jsa.analytics.databinding.FragmentReportBinding;

public class ReportFragment extends Fragment {

    FragmentReportBinding binding;
    public ReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportBinding.inflate(inflater,container,false);

        binding.item1.tableContainer.setVisibility(View.VISIBLE);

        return binding.getRoot();
    }
}
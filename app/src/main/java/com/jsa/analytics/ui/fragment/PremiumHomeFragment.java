package com.jsa.analytics.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsa.analytics.R;
import com.jsa.analytics.adapter.PremiumArticleAdapter;
import com.jsa.analytics.databinding.FragmentPremiumHomeBinding;


public class PremiumHomeFragment extends Fragment {

    FragmentPremiumHomeBinding binding;

    public PremiumHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPremiumHomeBinding.inflate(inflater,container,false);

        PremiumArticleAdapter adapter = new PremiumArticleAdapter(getContext());
        binding.articleListView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.articleListView.setAdapter(adapter);

        return binding.getRoot();
    }
}
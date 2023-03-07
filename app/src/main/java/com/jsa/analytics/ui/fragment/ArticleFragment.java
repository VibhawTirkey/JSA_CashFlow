package com.jsa.analytics.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsa.analytics.R;
import com.jsa.analytics.adapter.ArticleAdapter;
import com.jsa.analytics.databinding.FragmentArticleBinding;

public class ArticleFragment extends Fragment {

    FragmentArticleBinding binding;
    ArticleAdapter adapter;
    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArticleBinding.inflate(inflater,container,false);

        adapter = new ArticleAdapter(getActivity());
        binding.articleList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.articleList.setAdapter(adapter);

        return binding.getRoot();
    }
}
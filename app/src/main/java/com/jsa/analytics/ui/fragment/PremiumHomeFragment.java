package com.jsa.analytics.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsa.analytics.R;
import com.jsa.analytics.adapter.HomeTestimonialAdapter;
import com.jsa.analytics.adapter.HomeVideoAdapter;
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

        //Video Recycler View
        HomeVideoAdapter videoAdapter = new HomeVideoAdapter(getContext());
        binding.videoList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.videoList.setAdapter(videoAdapter);

        //Testimonial list
        HomeTestimonialAdapter testimonialAdapter = new HomeTestimonialAdapter(getContext());
        binding.testimonialList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        binding.testimonialList.setAdapter(testimonialAdapter);

        return binding.getRoot();
    }
}
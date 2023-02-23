package com.jsa.analytics.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsa.analytics.R;
import com.jsa.analytics.adapter.EventAdapter;
import com.jsa.analytics.databinding.FragmentEventBinding;

public class EventFragment extends Fragment {

    FragmentEventBinding binding;
    EventAdapter adapter;
    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventBinding.inflate(inflater,container,false);

        adapter = new EventAdapter(getContext());
        binding.eventList.setLayoutManager(new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL,false));
        binding.eventList.setAdapter(adapter);

        return binding.getRoot();
    }
}
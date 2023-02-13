package com.jsa.analytics.ui.fragment;

import static com.jsa.analytics.utils.Constants.OPEN_HOME;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jsa.analytics.callback.IFragmentCallback;
import com.jsa.analytics.databinding.FragmentGuidanceBinding;

public class GuidanceFragment extends Fragment {

    FragmentGuidanceBinding binding;
    IFragmentCallback iFragmentCallback;

    public GuidanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            iFragmentCallback = (IFragmentCallback) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context +"implementation failed");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGuidanceBinding.inflate(inflater,container,false);

        binding.toolbar.setNavigationOnClickListener(v -> iFragmentCallback.callBackAction(OPEN_HOME));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}
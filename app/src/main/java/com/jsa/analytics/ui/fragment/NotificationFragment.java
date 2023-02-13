package com.jsa.analytics.ui.fragment;

import static com.jsa.analytics.utils.Constants.OPEN_HOME;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsa.analytics.R;
import com.jsa.analytics.adapter.NotificationAdapter;
import com.jsa.analytics.callback.IFragmentCallback;
import com.jsa.analytics.databinding.FragmentNotificationBinding;

public class NotificationFragment extends Fragment {

    FragmentNotificationBinding binding;
    IFragmentCallback iFragmentCallback;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iFragmentCallback = (IFragmentCallback) getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater,container,false);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFragmentCallback.callBackAction(OPEN_HOME);
            }
        });

        NotificationAdapter adapter = new NotificationAdapter(getContext());
        binding.notificationList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.notificationList.addItemDecoration(new DividerItemDecoration(binding.notificationList.getContext(),DividerItemDecoration.VERTICAL));
        binding.notificationList.setAdapter(adapter);

        return binding.getRoot();
    }
}
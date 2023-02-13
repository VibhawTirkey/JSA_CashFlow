package com.jsa.analytics.ui.fragment;

import static com.jsa.analytics.utils.Constants.OPEN_DRAWER;
import static com.jsa.analytics.utils.Constants.OPEN_NOTIFICATION;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.jsa.analytics.R;
import com.jsa.analytics.callback.IFragmentCallback;
import com.jsa.analytics.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    FragmentHomeBinding binding;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    IFragmentCallback iToggleListener;
    int previousPosition = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

//    public static HomeFragment newInstance(String param1, String param2) {
//        BlankFragment fragment = new BlankFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            iToggleListener = (IFragmentCallback) context;
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
        binding = FragmentHomeBinding.inflate(inflater,container,false);

        binding.toolbar.setOnMenuItemClickListener(this);
        binding.toolbar.setNavigationOnClickListener(view -> iToggleListener.callBackAction(OPEN_DRAWER));

        fragment = new HomeItemFragment();
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(binding.container.getId(),fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();

        binding.image.setOnClickListener(v -> {
            fragment = new PremiumHomeFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(binding.container.getId(), fragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                switch (tab.getPosition()){
                    case 0:
                        fragment = new HomeItemFragment();
                        if (tab.getPosition() != previousPosition ){
                            ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        }
                        previousPosition = tab.getPosition();
                        break;
                    case 1:
                        fragment = new AgendaFragment();
                        if (previousPosition == 0){
                            ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                        }else {
                            ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        }
                        previousPosition = tab.getPosition();
                        break;
                    case 2:
                        fragment = new ContactFragment();
                        ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                        previousPosition = tab.getPosition();
                        break;
                }

                ft.replace(binding.container.getId(), fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return binding.getRoot();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                iToggleListener.callBackAction(OPEN_NOTIFICATION);
                return true;
        }
        return false;
    }

}
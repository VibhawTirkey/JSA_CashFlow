package com.jsa.analytics.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.jsa.analytics.R;
import com.jsa.analytics.adapter.BannerAdapter;
import com.jsa.analytics.adapter.EndlessPagerAdapter;
import com.jsa.analytics.adapter.HomeVideoAdapter;
import com.jsa.analytics.databinding.FragmentHomeBinding;
import com.jsa.analytics.databinding.FragmentHomeItemBinding;
import com.jsa.analytics.model.BannerModel;
import com.jsa.analytics.model.BannersModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeItemFragment extends Fragment {

    FragmentHomeItemBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    private List<BannersModel> bannerImgList = new ArrayList<>();
    private List<BannerModel> models = new ArrayList<>();
    private BannerAdapter bannerAdapter;
    private int currentPage = 1;
    private Timer timer;

    public HomeItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeItemBinding.inflate(inflater,container,false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //banner
        getBannerData();

        binding.bannerViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                stopBannerSlider();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    startBannerSlider();
                }
                return false;
            }
        });
        //banner

        //Video Recycler View
        HomeVideoAdapter adapter = new HomeVideoAdapter(getContext());
        binding.videoList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.videoList.setAdapter(adapter);
        return binding.getRoot();
    }

    private void getBannerData() {
        firebaseFirestore.collection("learning").document("banner").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    List<String> keys = new ArrayList<>();
                    JSONObject obj = new JSONObject(task.getResult().getData());
                    Iterator<String> iterator = obj.keys();
                    while(iterator.hasNext()){
                        String key = iterator.next();
                        keys.add(key);
                    }
                    for (String key:keys){
                        try {
                            JSONArray obj1 = obj.getJSONArray(key);
                            for (int i = 0;i<obj1.length();i++){
                                JSONObject object = obj1.getJSONObject(i);
                                BannerModel bannerModel = new Gson().fromJson(String.valueOf(object),BannerModel.class);
                                if (bannerModel.getVisible()){
                                    models.add(bannerModel);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    bannerAdapter = new BannerAdapter(getContext(), models);
                    EndlessPagerAdapter endlessPagerAdapter = new EndlessPagerAdapter(bannerAdapter, binding.bannerViewpager);
                    binding.bannerViewpager.setAdapter(endlessPagerAdapter);
                    binding.bannerViewpager.setClipToPadding(false);
                    binding.bannerViewpager.setCurrentItem(currentPage, false);
//                    binding.bannerViewpager.setPadding(50, 0, 50, 0);
//                    binding.bannerTab.setupWithViewPager(binding.bannerViewpager, false);
                    startBannerSlider();
                }else {
//                    Log.e(TAG, "onComplete: ",task.getException());
                }
            }
        });
    }

    private void startBannerSlider() {
        Handler handler = new Handler();
        Runnable update = () -> {
            if (binding.bannerViewpager.getCurrentItem()>=bannerImgList.size()){
                currentPage = 0;
            }
            binding.bannerViewpager.setCurrentItem(binding.bannerViewpager.getCurrentItem()+1,true);
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },6000,6000);
    }

    private void stopBannerSlider(){
//        timer.cancel();
    }


}
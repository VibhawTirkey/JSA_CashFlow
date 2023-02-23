package com.jsa.analytics.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.jsa.analytics.adapter.HomeTestimonialAdapter;
import com.jsa.analytics.adapter.HomeVideoAdapter;
import com.jsa.analytics.databinding.FragmentHomeBinding;
import com.jsa.analytics.databinding.FragmentHomeItemBinding;
import com.jsa.analytics.model.BannerModel;
import com.jsa.analytics.model.BannersModel;
import com.jsa.analytics.ui.AskYourQuestionActivity;
import com.jsa.analytics.ui.HelpActivity;
import com.jsa.analytics.ui.InsightActivity;
import com.jsa.analytics.ui.VideoActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeItemBinding.inflate(inflater,container,false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        setGreetState();
        //banner
        getBannerData();

        binding.bannerViewpager.setOnTouchListener((view, motionEvent) -> {
            stopBannerSlider();
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                startBannerSlider();
            }
            return false;
        });
        //banner

        //Video Recycler View
        HomeVideoAdapter adapter = new HomeVideoAdapter(getContext());
        binding.videoList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.videoList.setAdapter(adapter);

        //Testimonial list
        HomeTestimonialAdapter testimonialAdapter = new HomeTestimonialAdapter(getContext());
        binding.testimonialList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        binding.testimonialList.setAdapter(testimonialAdapter);

        binding.insight.setOnClickListener(v -> startActivity(new Intent(getContext(), InsightActivity.class)));
        binding.videoViewAll.setOnClickListener(v -> startActivity(new Intent(getContext(), VideoActivity.class)));
        binding.videos.setOnClickListener(v -> startActivity(new Intent(getContext(), VideoActivity.class)));
        binding.help.setOnClickListener(v -> startActivity(new Intent(getContext(), HelpActivity.class)));
        binding.askYourQuestion.setOnClickListener(v -> startActivity(new Intent(getContext(), AskYourQuestionActivity.class)));

        return binding.getRoot();
    }

    private void setGreetState() {
        Calendar c =Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >=0 && timeOfDay <12){
            binding.greetTime.setText("Good Morning");
            binding.greetTime.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.round_arrow_back,0);
        } else if (timeOfDay >= 12 && timeOfDay<16) {
            binding.greetTime.setText("Good Afternoon");
        }else if (timeOfDay >=16 && timeOfDay <24){
            binding.greetTime.setText("Good Evening");
        }
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
        timer.cancel();
    }


}
package com.jsa.analytics.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.jsa.analytics.R;
import com.jsa.analytics.adapter.HomeTestimonialAdapter;
import com.jsa.analytics.adapter.HomeVideoAdapter;
import com.jsa.analytics.adapter.PremiumArticleAdapter;
import com.jsa.analytics.databinding.FragmentPremiumHomeBinding;
import com.jsa.analytics.model.TestimonialModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PremiumHomeFragment extends Fragment {

    FragmentPremiumHomeBinding binding;
    FirebaseFirestore firebaseFirestore;
    List<TestimonialModel> testimonialModels = new ArrayList<>();
    HomeTestimonialAdapter testimonialAdapter;
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

        firebaseFirestore = FirebaseFirestore.getInstance();

        getTestimonialData();

        PremiumArticleAdapter adapter = new PremiumArticleAdapter(getContext());
        binding.articleListView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.articleListView.setAdapter(adapter);

        //Video Recycler View
        HomeVideoAdapter videoAdapter = new HomeVideoAdapter(getContext());
        binding.videoList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.videoList.setAdapter(videoAdapter);


        return binding.getRoot();
    }

    private void getTestimonialData() {
        firebaseFirestore.collection("learning").document("testimonial").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    List<String> keys = new ArrayList<>();
                    JSONObject obj = new JSONObject(task.getResult().getData());
                    Iterator<String> iterator = obj.keys();
                    while (iterator.hasNext()){
                        String key = iterator.next();
                        keys.add(key);
                    }
                    for (String key:keys){
                        try {
                            JSONObject jsonObject = obj.getJSONObject(key);
                            TestimonialModel testimonialModel = new Gson().fromJson(String.valueOf(jsonObject),TestimonialModel.class);
                            testimonialModels.add(testimonialModel);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    testimonialAdapter = new HomeTestimonialAdapter(getContext(),testimonialModels);
                    binding.testimonialList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                    binding.testimonialList.setAdapter(testimonialAdapter);

                }else {

                }
            }
        });
    }
}
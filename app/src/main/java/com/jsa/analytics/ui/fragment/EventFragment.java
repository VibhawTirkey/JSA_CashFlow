package com.jsa.analytics.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.jsa.analytics.adapter.EventAdapter;
import com.jsa.analytics.databinding.FragmentEventBinding;
import com.jsa.analytics.model.EventModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventFragment extends Fragment {

    FragmentEventBinding binding;
    EventAdapter adapter;
    List<EventModel> eventModels = new ArrayList<>();
    FirebaseFirestore firebaseFirestore;
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

        firebaseFirestore = FirebaseFirestore.getInstance();

        getEventData();
        return binding.getRoot();
    }

    private void getEventData() {
        firebaseFirestore.collection("learning").document("events").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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
                            EventModel eventModel = new Gson().fromJson(String.valueOf(jsonObject), EventModel.class);
                            eventModels.add(eventModel);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    adapter = new EventAdapter(getContext(),eventModels);
                    binding.eventList.setLayoutManager(new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL,false));
                    binding.eventList.setAdapter(adapter);
                }
            }
        });
    }
}
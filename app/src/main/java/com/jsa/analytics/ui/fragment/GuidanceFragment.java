package com.jsa.analytics.ui.fragment;

import static com.jsa.analytics.utils.Constants.OPEN_HOME;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsa.analytics.adapter.GuidanceAdapter;
import com.jsa.analytics.callback.IFragmentCallback;
import com.jsa.analytics.databinding.FragmentGuidanceBinding;
import com.jsa.analytics.model.GuidanceModel;
import com.jsa.analytics.utils.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GuidanceFragment extends Fragment {

    FragmentGuidanceBinding binding;
    IFragmentCallback iFragmentCallback;
    GuidanceAdapter adapter;
    List<GuidanceModel> list;
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

        list = new ArrayList<>();
//        list.add(new GuidanceModel("BONUS 1","On-demand Query Resolution (₹15,000 Free!) Just log-on to JSA Online Portal to put your query and get instant help from our 100+ experts - as many time as you want."));
        try {
            JSONObject obj = new JSONObject(Utilities.loadJSONFromAsset(getActivity(), "JSA.json"));
            JSONArray jsonArray = obj.getJSONArray("guidance");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo_inside = jsonArray.getJSONObject(i);
                String title = jo_inside.getString("title");
                String description = jo_inside.getString("description");

                //Add your values in your `ArrayList` as below:
                list.add(new GuidanceModel(title,description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new GuidanceAdapter(getContext(),list);
        binding.guidanceList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.guidanceList.setAdapter(adapter);

        return binding.getRoot();
    }
}
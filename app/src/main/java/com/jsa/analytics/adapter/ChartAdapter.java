package com.jsa.analytics.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.slider.LabelFormatter;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ItemChartBinding;
import com.jsa.analytics.model.ChartModel;

import java.util.ArrayList;
import java.util.List;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ChartViewHolder> {

    Context context;
    List<ChartModel> chartModels;
    int[] colors = {R.color.app_blue,R.color.a_y_q_red,R.color.a_y_q_green};

    public ChartAdapter(Context context, List<ChartModel> chartModels) {
        this.context = context;
        this.chartModels = chartModels;
    }

    @NonNull
    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChartBinding binding = ItemChartBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ChartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder holder, int position) {
        ChartModel model = chartModels.get(position);
        holder.binding.title.setText(model.getTitle());
        ArrayList<BarEntry> data = new ArrayList<>();
        data.add(new BarEntry(1,model.getActualData().intValue()));
        data.add(new BarEntry(2,model.getPlanData().intValue()));
        BarDataSet barDataSet = new BarDataSet(data,"Actual Plan");
        barDataSet.setColors(new int[]{Color.argb(255,108,93,211),Color.argb(255,160,215,231)});
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        holder.binding.chart.setFitBars(true);
        holder.binding.chart.setData(barData);
        holder.binding.chart.getDescription().setText(model.getTitle());
        holder.binding.chart.animateY(1500);
    }

    @Override
    public int getItemCount() {
        return chartModels.size();
    }

    public class ChartViewHolder extends RecyclerView.ViewHolder {
        ItemChartBinding binding;
        public ChartViewHolder(ItemChartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

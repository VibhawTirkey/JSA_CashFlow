package com.jsa.analytics.utils;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.jsa.analytics.databinding.ItemNetworkStatusBinding;

public class NetworkStateUtil extends BroadcastReceiver {
    private static final String INTERNET_CONNECTED = "internet_on.json";
    private static final String INTERNET_DISCONNECTED = "internet_off.json";
    @Override
    public void onReceive(Context context, Intent intent) {
        ItemNetworkStatusBinding binding = ItemNetworkStatusBinding.inflate(LayoutInflater.from(context),null,false);
        if (!Utilities.isConnectedToInternet(context)){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(binding.getRoot());

            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setGravity(Gravity.CENTER);

            binding.lottieNetworkConnection.setAnimation(INTERNET_DISCONNECTED);
            binding.lottieNetworkConnection.playAnimation();
            binding.lottieNetworkConnection.loop(true);
            binding.netMessage.setText("Network Connection Lost");
            binding.retry.setOnClickListener(v -> {
                alertDialog.dismiss();
                Toast.makeText(context, "Kindly Check Your Wifi or Mobile Data is ON!", Toast.LENGTH_SHORT).show();
                onReceive(context,intent);
            });
        }
    }
}

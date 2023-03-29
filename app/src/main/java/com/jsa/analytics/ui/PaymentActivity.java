package com.jsa.analytics.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jsa.analytics.R;
import com.jsa.analytics.databinding.ActivityPaymentBinding;
import com.jsa.analytics.databinding.ItemPaymentProcessBinding;
import com.jsa.analytics.utils.Constants;
import com.razorpay.Checkout;
import com.razorpay.Payment;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.razorpay.RazorpayClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PaymentActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    ActivityPaymentBinding binding;
    private JSONObject jsonObject;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    DocumentReference paymentReference;
    DocumentReference userReference;
    String orderId, amount;
    String fullName, email, phone, planName, validity, expiryDate, initialExpiryDate;
    ItemPaymentProcessBinding paymentProcessBinding;

    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        paymentReference = firebaseFirestore.collection("payments").document(firebaseUser.getUid());
        userReference = firebaseFirestore.collection("users").document(firebaseUser.getUid());
        amount = getIntent().getStringExtra("amount");
        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.continuePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        paymentProcessBinding.ongoing.setVisibility(View.GONE);
                        paymentProcessBinding.failed.setVisibility(View.VISIBLE);
                    }
                },3000);

                /*new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        paymentProcessBinding.failed.setVisibility(View.GONE);
                        paymentProcessBinding.success.setVisibility(View.VISIBLE);
                    }
                },6000);*/
            }
        });


    }

    private void showBottomSheetDialog() {
        paymentProcessBinding = ItemPaymentProcessBinding.inflate(getLayoutInflater());
        bottomSheetDialog = new BottomSheetDialog(this, R.style.TransparentDialog);
        bottomSheetDialog.setContentView(paymentProcessBinding.getRoot());
        paymentProcessBinding.getRoot().getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();
        paymentProcessBinding.gotoHomepage.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            bottomSheetDialog.dismiss();
        });
        paymentProcessBinding.retry.setOnClickListener(v -> bottomSheetDialog.dismiss());
    }

    private void callRazorPay(String fullName,String amount,String email,String phone) {
        Checkout checkout = new Checkout();
        checkout.setKeyID(Constants.RAZORPAY_KEY_ID);

        try {
            jsonObject = new JSONObject();
            jsonObject.put("name", fullName);
            jsonObject.put("description", "1048" + ": " + "Astro Express Wallet Recharge");
            jsonObject.put("send_sms_hash", false);
            jsonObject.put("allow_rotation", false);
            jsonObject.put("image", "https://astroexpress.in/img/logo.png");
            jsonObject.put("currency", "INR");
//            jsonObject.put("order_id", orderId);
            jsonObject.put("amount", Double.parseDouble(amount) * 100);
            JSONObject jsonObjectPrefill = new JSONObject();
            jsonObjectPrefill.put("email", email);
            jsonObjectPrefill.put("contact", phone);
            jsonObject.put("prefill", jsonObjectPrefill);
            checkout.open(PaymentActivity.this, jsonObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        new PaymentVerify(paymentData.getPaymentId()).execute();
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Map<String, Object> data = new HashMap<>();
        data.put(orderId + ".status","Failed");
        data.put(orderId + ".gatewayPaymentRequest", jsonObject.toString());
        data.put(orderId + ".gatewayPaymentError", paymentData.getData().toString());
        paymentReference.update(data).addOnCompleteListener(task -> {

        });
        paymentProcessBinding.ongoing.setVisibility(View.GONE);
        paymentProcessBinding.failed.setVisibility(View.VISIBLE);
    }

    private class PaymentVerify extends AsyncTask<String, Integer, Payment>{
        String paymentId;

        public PaymentVerify(String paymentId) {
            this.paymentId = paymentId;
        }

        @Override
        protected Payment doInBackground(String... strings) {
            try {
                RazorpayClient razorpayClient = new RazorpayClient(Constants.RAZORPAY_KEY_ID, Constants.RAZORPAY_MERCHANT_KEY);
                return razorpayClient.payments.fetch(paymentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Payment payment) {
//            super.onPostExecute(payment);
            try {
                if (payment != null) {
                    JSONObject paymentResult = payment.toJson();
                    Map<String, Object> paymentData = new HashMap<>();
                    paymentData.put(orderId + ".paymentTransactionId", paymentResult.getString("id"));
                    paymentData.put(orderId + ".paymentStatus", paymentResult.getString("status"));
                    paymentData.put(orderId + ".paymentMethod", paymentResult.getString("method"));
                    paymentData.put(orderId + ".gatewayPaymentResult", paymentResult.toString());
                    paymentData.put(orderId + ".gatewayPaymentRequest", jsonObject.toString());
                    paymentData.put(orderId + ".status","Success");
                    paymentReference.update(paymentData).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("userType", "active");
                            userData.put("planType", planName);
                            userData.put("planExpiryDate", expiryDate);
                            userReference.update(userData).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()){
                                    paymentProcessBinding.ongoing.setVisibility(View.GONE);
                                    TransitionManager.beginDelayedTransition(paymentProcessBinding.getRoot(), new AutoTransition());
                                    paymentProcessBinding.success.setVisibility(View.VISIBLE);
                                    new Timer().schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            bottomSheetDialog.dismiss();
                                            startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                                            finish();
                                        }
                                    },2000);
                                }
                            });
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
package com.jsa.analytics.ui;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;

import static com.jsa.analytics.utils.Constants.OPEN_DRAWER;
import static com.jsa.analytics.utils.Constants.OPEN_HOME;
import static com.jsa.analytics.utils.Constants.OPEN_NOTIFICATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.jsa.analytics.R;
import com.jsa.analytics.callback.IFragmentCallback;
import com.jsa.analytics.databinding.ActivityDashboardBinding;
import com.jsa.analytics.ui.fragment.GuidanceFragment;
import com.jsa.analytics.ui.fragment.HomeFragment;
import com.jsa.analytics.ui.fragment.HomeItemFragment;
import com.jsa.analytics.ui.fragment.InsightFragment;
import com.jsa.analytics.ui.fragment.NotificationFragment;
import com.jsa.analytics.ui.fragment.PremiumHomeFragment;
import com.jsa.analytics.ui.fragment.ProfileFragment;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, IFragmentCallback, NavigationView.OnNavigationItemSelectedListener {

    ActivityDashboardBinding binding;
    FragmentTransaction transaction;

    FirebaseAuth firebaseAuth;
    HomeFragment homeFragment;
    InsightFragment insightFragment;
    ProfileFragment profileFragment;
    GuidanceFragment guidanceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        homeFragment = new HomeFragment();
        insightFragment = new InsightFragment();
        profileFragment = new ProfileFragment();
        guidanceFragment = new GuidanceFragment();

//        getSupportActionBar().hide();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.homeContainer.getId(),new HomeFragment());
        transaction.commit();
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        binding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        switch (item.getItemId()){
            case R.id.home:
                transaction.replace(binding.homeContainer.getId(),homeFragment);
                transaction.commit();
                return true;
            case R.id.edit_profile:
                startActivity(new Intent(getApplicationContext(),EditProfileActivity.class));
                break;
            case R.id.report:
                transaction.replace(binding.homeContainer.getId(),insightFragment);
                transaction.commit();
                return true;
            case R.id.guidance:
                transaction.replace(binding.homeContainer.getId(),guidanceFragment);
                transaction.commit();
                return true;
            case R.id.profile:
                transaction.replace(binding.homeContainer.getId(),profileFragment);
                transaction.commit();
                return true;
            case R.id.videos:
                startActivity(new Intent(getApplicationContext(),VideoActivity.class));
                break;
            /*case R.id.profile:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                break;*/
            case R.id.dashboard:
                startActivity(new Intent(getApplicationContext(), CashFlowDashboardActivity.class));
                break;
            case R.id.settings:
                transaction.replace(binding.homeContainer.getId(),new PremiumHomeFragment());
                transaction.commit();
                break;
            case R.id.logout:
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                break;
            default:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void callBackAction(String action) {
        switch (action){
            case OPEN_DRAWER:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                break;
            case OPEN_NOTIFICATION:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                transaction.replace(binding.homeContainer.getId(),new NotificationFragment());
                transaction.commit();
                break;
            case OPEN_HOME:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                transaction.replace(binding.homeContainer.getId(),homeFragment);
                transaction.commit();
                break;
            default:
        }
    }
}
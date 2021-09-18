package com.example.zakat.views.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.example.zakat.R;
import com.example.zakat.databinding.ActivityAuthBinding;
import com.example.zakat.models.core.User;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.auth.AuthViewModel;
import com.example.zakat.views.admin.AdminHome;
import com.example.zakat.views.auth.register.RegisterActivity;
import com.example.zakat.views.user.UserHome;
import com.google.firebase.auth.FirebaseAuth;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {
    private static final String TAG = "AuthActivity";
    ActivityAuthBinding binding;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = new ViewModelProvider(this,providerFactory).get(AuthViewModel.class);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptToLogin();
            }
        });

        binding.registerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AuthActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        setUpInitialLogo();

        ObserveChangesInLogin();
    }

    private void ObserveChangesInLogin(){
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource !=null){
                    switch (userAuthResource.status){
                        case LOADING:
                            //loading
                            Log.d(TAG, "onChanged: Loading called");
                            showProgressBar(true);
                            break;
                        case AUTHENTICATED:
                            showProgressBar(false);
                            if (userAuthResource.data != null) {
                                Log.d(TAG, "onChanged: "+userAuthResource.data.getUid());
                                loginSuccess(userAuthResource.data);
                            }
                            Log.d(TAG, "onChanged: Auth Success");
                            break;
                        case ERROR:
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: Auth Fail");
                            break;
                        case NOT_AUTHENTICATED:
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: Wrong pass or user name");
                            break;
                    }
                }
            }
        });
    }


    private void attemptToLogin() {
        viewModel.authenticateWithEmailPassword(binding.userIdInput.getText().toString().trim(),binding.userPasswordInput.getText().toString().trim());
    }

    private void setUpInitialLogo(){
        requestManager.load(R.drawable.ic_baseline_account_circle_24)
                .into(binding.loginLogo);
    }

    private void showProgressBar(boolean isVisible){
        if(isVisible){
            binding.progressCircular.progressBar.setVisibility(View.VISIBLE);
        }
        else
            binding.progressCircular.progressBar.setVisibility(View.INVISIBLE);
    }


    private void loginSuccess(User user){
        if(user.getType().contentEquals("admin")){
            Log.d(TAG, "loginSuccess: type Admin");
            Intent intent = new Intent(this, AdminHome.class);
            startActivity(intent);
        }
        else {
            Log.d(TAG, "loginSuccess: type user");
            Intent intent = new Intent(this, UserHome.class);
            startActivity(intent);
        }
        finish();


    }
}
package com.example.zakat.views.auth.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.zakat.databinding.ActivityRegisterBinding;
import com.example.zakat.models.RegisterModel;
import com.example.zakat.models.core.User;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.auth.register.RegisterViewModel;
import com.example.zakat.views.auth.AuthActivity;
import com.example.zakat.views.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class RegisterActivity extends DaggerAppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this,providerFactory).get(RegisterViewModel.class);



        binding.backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });




        binding.registerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.userFullName.getText().toString().trim();
                String email = binding.userEmail.getText().toString().trim();
                String password =binding.userPassword.getText().toString().trim();
                RegisterModel model = new RegisterModel(name,email,password);

                viewModel.registerWithEmailPassword(model);
            }
        });

        checkUserIsCompleted();

    }

    private void checkUserIsCompleted(){
        viewModel.registrationSuccessful().removeObservers(this);
        viewModel.registrationSuccessful().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource !=null){
                    switch (userAuthResource.status){
                        case LOADING:
                            Log.d(TAG, "onChanged: loading");
                            break;
                        case AUTHENTICATED:
                            Log.d(TAG, "onChanged: Auth success");
                            backToLogin();
                            break;
                        case ERROR:
                            Log.d(TAG, "onChanged: Error");
                            break;
                        case NOT_AUTHENTICATED:
                            Log.d(TAG, "onChanged: Not Authenticated");
                            break;
                    }
                }
            }
        });
    }

    private void backToLogin(){
        Intent intent = new Intent(RegisterActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
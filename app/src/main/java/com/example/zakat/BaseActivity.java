package com.example.zakat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.zakat.models.core.User;
import com.example.zakat.views.auth.AuthActivity;
import com.example.zakat.views.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {
    public static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        observeUserLoginState();
    }

    public void observeUserLoginState(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource !=null){
                    switch (userAuthResource.status){
                        case LOADING:
                            //loading
                            Log.d(TAG, "onChanged: Loading called");
                            break;
                        case AUTHENTICATED:
                            Log.d(TAG, "onChanged: Auth Success");
                            break;
                        case ERROR:
                            Log.d(TAG, "onChanged: Auth Fail");
                            break;
                        case NOT_AUTHENTICATED:
                            Log.d(TAG, "onChanged: Wrong pass or user name");
                            navToLogin();
                            break;
                    }
                }
            }
        });
    }

    private void navToLogin(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}

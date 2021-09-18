package com.example.zakat.repository.auth.register;

import android.util.Log;

import com.example.zakat.repository.auth.AuthRepo;

import javax.inject.Inject;

public class UserRegistrationRepo {
    private static final String TAG = "UserRegistrationRepo";
    private AuthRepo repo;

    @Inject
    public UserRegistrationRepo(AuthRepo repo) {
    }

    public void registerUserWithEmailPassword(String email,String password){
        Log.d(TAG, "registerUserWithEmailPassword: ");
    }
    public void registerUserWithGoogle(){
        Log.d(TAG, "registerUserWithGoogle: doing");
    }
    public void registerUserWithPhone(){
        Log.d(TAG, "registerUserWithPhone: doing");
    }

}

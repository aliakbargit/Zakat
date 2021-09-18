package com.example.zakat.viewModels.user.fragments;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.zakat.repository.auth.AuthRepo;
import com.example.zakat.repository.user.UserRepo;

import javax.inject.Inject;

public class UserApplyViewModel extends ViewModel {
    private static final String TAG = "UserApplyViewModel";
    private AuthRepo repo;
    private UserRepo userRepo;

    @Inject
    public UserApplyViewModel(AuthRepo repo, UserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;

        Log.d(TAG, "UserApplyViewModel: Ready");
    }

    public String getUser(){
        return repo.getUserUid();
    }
}

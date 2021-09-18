package com.example.zakat.viewModels.user.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.models.user.UserProfile;
import com.example.zakat.repository.user.UserRepo;
import com.example.zakat.util.Resource;

import javax.inject.Inject;

public class UserProfileViewModel extends ViewModel {
    private static final String TAG = "UserProfileViewModel";
    private UserRepo repo;

    @Inject
    public UserProfileViewModel(UserRepo repo) {
        this.repo = repo;
    }

    public LiveData<Resource<UserProfile>> getUserProfile(){
        return repo.getUserProfileDetails();
    }
}

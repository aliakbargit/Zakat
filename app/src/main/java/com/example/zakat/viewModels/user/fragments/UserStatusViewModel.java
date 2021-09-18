package com.example.zakat.viewModels.user.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.models.Progress;
import com.example.zakat.models.core.ApplicationToSubmit;
import com.example.zakat.models.user.UserProfile;
import com.example.zakat.repository.user.UserRepo;
import com.example.zakat.util.Resource;

import javax.inject.Inject;

public class UserStatusViewModel extends ViewModel {
    private static final String TAG = "UserStatusViewModel";
    private UserRepo repo;

    @Inject
    public UserStatusViewModel(UserRepo repo)
    {
        this.repo = repo;
        Log.d(TAG, "UserStatusViewModel: Ready");
    }

    public LiveData<Resource<Progress>> getApplicationStatus(){
        return repo.getApplicationStatus();
    }
    public LiveData<Resource<ApplicationToSubmit>> getApplicationInfo(){
        return repo.getApplicationStatusInfo();
    }


}

package com.example.zakat.viewModels.user;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class UserHomeViewModel extends ViewModel {
    private static final String TAG = "UserHomeViewModel";

    @Inject
    public UserHomeViewModel() {
        Log.d(TAG, "UserHomeViewModel: Ready");
    }
}

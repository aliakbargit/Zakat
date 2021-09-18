package com.example.zakat.viewModels.admin;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class AdminHomeViewModel extends ViewModel {
    private static final String TAG = "AdminHomeViewModel";

    @Inject
    public AdminHomeViewModel() {
        Log.d(TAG, "AdminHomeViewModel: Ready");
    }

}

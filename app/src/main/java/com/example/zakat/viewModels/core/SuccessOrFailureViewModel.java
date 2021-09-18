package com.example.zakat.viewModels.core;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class SuccessOrFailureViewModel extends ViewModel {
    private static final String TAG = "SuccessOrFailureView";

    @Inject
    public SuccessOrFailureViewModel() {
        Log.d(TAG, "SuccessOrFailureViewModel: ");
    }
}

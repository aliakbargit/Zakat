package com.example.zakat.viewModels.user.fragments;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class UserArticleDetailsViewModel extends ViewModel {
    private static final String TAG = "UserArticleDetailsViewM";

    @Inject
    public UserArticleDetailsViewModel() {
        Log.d(TAG, "UserArticleDetailsViewModel: View details");
    }
}

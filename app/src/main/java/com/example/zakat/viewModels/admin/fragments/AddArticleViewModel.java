package com.example.zakat.viewModels.admin.fragments;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.zakat.models.admin.AddArticle;

import javax.inject.Inject;

public class AddArticleViewModel extends ViewModel {
    private static final String TAG = "AddArticleViewModel";

    @Inject
    public AddArticleViewModel() {
        Log.d(TAG, "AddArticleViewModel: Ready");
    }


    public void addArticle(AddArticle article){

    }
}

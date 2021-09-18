package com.example.zakat.viewModels.admin.fragments;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.zakat.models.admin.AddSlider;

import javax.inject.Inject;

public class AddSliderViewModel extends ViewModel {
    private static final String TAG = "AddSliderViewModel";

    @Inject
    public AddSliderViewModel() {
        Log.d(TAG, "AddSliderViewModel: Ready");
    }

    public void addSlider(AddSlider slider){

    }

}

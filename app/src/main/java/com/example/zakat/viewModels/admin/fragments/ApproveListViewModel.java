package com.example.zakat.viewModels.admin.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.models.ApplicationModel;
import com.example.zakat.util.Resource;

import java.util.List;

import javax.inject.Inject;

public class ApproveListViewModel extends ViewModel {
    private static final String TAG = "ApproveListViewModel";
    private LiveData<Resource<List<ApplicationModel>>> approveListLive;

    @Inject
    public ApproveListViewModel() {
        Log.d(TAG, "ApproveListViewModel: Ready");
    }

    public LiveData<Resource<List<ApplicationModel>>> getApproveListLive(){
        return null;
    }
}

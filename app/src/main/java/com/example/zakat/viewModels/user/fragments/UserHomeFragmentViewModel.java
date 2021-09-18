package com.example.zakat.viewModels.user.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zakat.models.Featured;
import com.example.zakat.models.core.ZakatArticle;
import com.example.zakat.repository.user.UserRepo;
import com.example.zakat.util.Resource;

import java.util.List;

import javax.inject.Inject;

public class UserHomeFragmentViewModel extends ViewModel {
    private LiveData<Resource<List<ZakatArticle>>> articleList;
    private LiveData<Resource<List<Featured>>> sliderData;
    private static final String TAG = "UserHomeFragmentView";
    private UserRepo repo;

    @Inject
    public UserHomeFragmentViewModel(UserRepo repo) {
        Log.d(TAG, "UserHomeFragmentViewModel: Ready");
        this.repo=repo;
    }

    public LiveData<Resource<List<ZakatArticle>>> getListOfArticle(){
        if(articleList == null){
            articleList = repo.getArticleList();
        }
        return articleList;
    }

    public LiveData<Resource<List<Featured>>> getSliderList(){
        if(sliderData==null){
            sliderData=repo.getSliderList();
        }
        return sliderData;
    }
}

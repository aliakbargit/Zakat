package com.example.zakat.di.admin;

import androidx.lifecycle.ViewModel;

import com.example.zakat.di.ViewModelKey;
import com.example.zakat.viewModels.admin.fragments.AddArticleViewModel;
import com.example.zakat.viewModels.admin.fragments.AddSliderViewModel;
import com.example.zakat.viewModels.admin.fragments.AdminProfileViewModel;
import com.example.zakat.viewModels.admin.fragments.ApproveListViewModel;
import com.example.zakat.viewModels.admin.fragments.PendingListViewModel;
import com.example.zakat.viewModels.admin.fragments.RejectListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AdminViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AdminProfileViewModel.class)
    public abstract ViewModel bindAdminProfileViewModel(AdminProfileViewModel adminProfileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddArticleViewModel.class)
    public abstract ViewModel bindAdminAddArticleViewModel(AddArticleViewModel addArticleViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddSliderViewModel.class)
    public abstract ViewModel bindAdminAddSliderViewModel(AddSliderViewModel addSliderViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ApproveListViewModel.class)
    public abstract ViewModel bindAdminApproveListViewModel(ApproveListViewModel approveListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PendingListViewModel.class)
    public abstract ViewModel bindAdminPendingListViewModel(PendingListViewModel pendingListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RejectListViewModel.class)
    public abstract ViewModel bindAdminAddRejectViewModel(RejectListViewModel rejectListViewModel);


}

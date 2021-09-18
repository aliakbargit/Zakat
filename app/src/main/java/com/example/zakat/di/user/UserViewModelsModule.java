package com.example.zakat.di.user;

import androidx.lifecycle.ViewModel;

import com.example.zakat.di.ViewModelKey;
import com.example.zakat.viewModels.user.fragments.UserApplyViewModel;
import com.example.zakat.viewModels.user.fragments.UserArticleDetailsViewModel;
import com.example.zakat.viewModels.user.fragments.UserHomeFragmentViewModel;
import com.example.zakat.viewModels.user.fragments.UserProfileViewModel;
import com.example.zakat.viewModels.user.fragments.UserStatusViewModel;
import com.example.zakat.viewModels.user.fragments.type.CommonTypeViewModel;
import com.example.zakat.viewModels.user.fragments.type.EducationTypeViewModel;
import com.example.zakat.viewModels.user.fragments.type.RentTypeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class UserViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserHomeFragmentViewModel.class)
    public abstract ViewModel bindUserHomeFragmentViewModel(UserHomeFragmentViewModel userHomeFragmentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel.class)
    public abstract ViewModel bindUserProfileViewModel(UserProfileViewModel userProfileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserApplyViewModel.class)
    public abstract ViewModel bindUserApplyViewModel(UserApplyViewModel userApplyViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserStatusViewModel.class)
    public abstract ViewModel bindUserStatusViewModel(UserStatusViewModel userStatusViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserArticleDetailsViewModel.class)
    public abstract ViewModel bindUserArticleDetailsViewModel(UserArticleDetailsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CommonTypeViewModel.class)
    public abstract ViewModel bindCommonTypeViewModel(CommonTypeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EducationTypeViewModel.class)
    public abstract ViewModel bindEducationTypeViewModel(EducationTypeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RentTypeViewModel.class)
    public abstract ViewModel bindRentTypeViewModel(RentTypeViewModel viewModel);



}

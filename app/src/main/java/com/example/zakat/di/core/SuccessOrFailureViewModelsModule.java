package com.example.zakat.di.core;

import androidx.lifecycle.ViewModel;

import com.example.zakat.di.ViewModelKey;
import com.example.zakat.viewModels.core.SuccessOrFailureViewModel;
import com.example.zakat.viewModels.user.fragments.UserHomeFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SuccessOrFailureViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(SuccessOrFailureViewModel.class)
    public abstract ViewModel bindSuccessOrFailureViewModel(SuccessOrFailureViewModel viewModel);
}


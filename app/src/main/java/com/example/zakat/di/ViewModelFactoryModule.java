package com.example.zakat.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.zakat.viewModels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory modelProviderFactory);
}

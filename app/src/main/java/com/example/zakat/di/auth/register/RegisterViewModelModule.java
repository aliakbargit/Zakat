package com.example.zakat.di.auth.register;

import androidx.lifecycle.ViewModel;

import com.example.zakat.di.ViewModelKey;
import com.example.zakat.viewModels.auth.register.RegisterViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class RegisterViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    public abstract ViewModel bindRegisterViewModel(RegisterViewModel registerViewModel);

}

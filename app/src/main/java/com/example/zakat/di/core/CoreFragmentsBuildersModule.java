package com.example.zakat.di.core;

import com.example.zakat.views.core.SuccessOrFailure;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CoreFragmentsBuildersModule {
    @ContributesAndroidInjector(
            modules = {
                    SuccessOrFailureViewModelsModule.class
            }
    )
    abstract SuccessOrFailure contributesSuccessOrFailure();
}

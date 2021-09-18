package com.example.zakat.di;

import com.example.zakat.di.admin.AdminFragmentsBuildersModule;
import com.example.zakat.di.admin.AdminViewModelsModule;
import com.example.zakat.di.auth.AuthViewModelModule;
import com.example.zakat.di.auth.register.RegisterViewModelModule;
import com.example.zakat.di.user.UserFragmentsBuildersModule;
import com.example.zakat.di.user.UserViewModelsModule;
import com.example.zakat.views.admin.AdminHome;
import com.example.zakat.views.admin.activities.ApplicationDetailsViewResult;
import com.example.zakat.views.auth.AuthActivity;
import com.example.zakat.views.auth.register.RegisterActivity;
import com.example.zakat.views.user.UserHome;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules =
            {
                    AuthViewModelModule.class,
            })
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {
                    AdminFragmentsBuildersModule.class,
                    AdminViewModelsModule.class,
            }
    )
    abstract AdminHome contributeAdminHomeActivity();

    @ContributesAndroidInjector(
            modules = {UserFragmentsBuildersModule.class,
                    UserViewModelsModule.class
            }
    )
    abstract UserHome contributeUserHomeActivity();

    @ContributesAndroidInjector(
            modules = {
                    RegisterViewModelModule.class
            }
    )
    abstract RegisterActivity contributeRegisterActivity();

    @ContributesAndroidInjector
    abstract ApplicationDetailsViewResult contributesAdminApplicationDetails();

}

package com.example.zakat.di.admin;

import com.example.zakat.views.admin.activities.ApplicationDetailsViewResult;
import com.example.zakat.views.admin.fragments.AdminAddArticleFragment;
import com.example.zakat.views.admin.fragments.AdminAddSliderFragment;
import com.example.zakat.views.admin.fragments.AdminApproveListFragment;
import com.example.zakat.views.admin.fragments.AdminPendingListFragment;
import com.example.zakat.views.admin.fragments.AdminProfileFragment;
import com.example.zakat.views.admin.fragments.AdminRejectListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AdminFragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract AdminProfileFragment contributesAdminProfileFragment();

    @ContributesAndroidInjector
    abstract AdminAddArticleFragment contributesAdminAddArticleFragment();

    @ContributesAndroidInjector
    abstract AdminAddSliderFragment contributesAdminAddSliderFragment();

    @ContributesAndroidInjector
    abstract AdminApproveListFragment contributesAdminApproveListFragment();

    @ContributesAndroidInjector
    abstract AdminRejectListFragment contributesAdminRejectListFragment();

    @ContributesAndroidInjector
    abstract AdminPendingListFragment contributesAdminPendingListFragment();


}

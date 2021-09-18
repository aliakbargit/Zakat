package com.example.zakat.di.user;

import com.example.zakat.views.user.fragments.UserApplyPhaseOneFragment;
import com.example.zakat.views.user.fragments.UserApplyPhaseTwoFragment;
import com.example.zakat.views.user.fragments.UserArticleDetails;
import com.example.zakat.views.user.fragments.UserCheckStatusFragment;
import com.example.zakat.views.user.fragments.UserHomeFragment;
import com.example.zakat.views.user.fragments.UserProfileFragment;
import com.example.zakat.views.user.fragments.type.CommonTypeFragment;
import com.example.zakat.views.user.fragments.type.EducationTypeFragment;
import com.example.zakat.views.user.fragments.type.RentHouseTypeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UserFragmentsBuildersModule {

    @ContributesAndroidInjector
    abstract UserHomeFragment contributesUserHomeFragment();

    @ContributesAndroidInjector
    abstract UserProfileFragment contributesUserProfileFragment();

    @ContributesAndroidInjector
    abstract UserApplyPhaseOneFragment contributesUserApplyPhaseOneFragment();

    @ContributesAndroidInjector
    abstract UserApplyPhaseTwoFragment contributesUserApplyPhaseTwoFragment();

    @ContributesAndroidInjector
    abstract UserCheckStatusFragment contributesUserCheckStatusFragment();

    @ContributesAndroidInjector
    abstract UserArticleDetails contributesUserArticleDetails();

    @ContributesAndroidInjector
    abstract CommonTypeFragment contributesCommonTypeFragment();

    @ContributesAndroidInjector
    abstract RentHouseTypeFragment contributesRentHouseTypeFragment();

    @ContributesAndroidInjector
    abstract EducationTypeFragment contributesEducationTypeFragment();

}

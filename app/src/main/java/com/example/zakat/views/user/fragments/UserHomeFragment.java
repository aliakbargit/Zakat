package com.example.zakat.views.user.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.zakat.R;
import com.example.zakat.databinding.UserHomeBinding;
import com.example.zakat.models.Featured;
import com.example.zakat.models.core.ZakatArticle;
import com.example.zakat.util.Resource;
import com.example.zakat.viewModels.ViewModelProviderFactory;
import com.example.zakat.viewModels.user.fragments.UserHomeFragmentViewModel;
import com.example.zakat.views.core.ImageSliderAdapter;
import com.example.zakat.views.core.OnClickListeners;
import com.example.zakat.views.user.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.example.zakat.util.Constrains.ARTICLE_KEY;


public class UserHomeFragment extends DaggerFragment implements OnClickListeners {
    private static final String TAG = "UserHome";
    private UserHomeBinding binding;
    private UserHomeFragmentViewModel viewModel;
    private NavController navController;

    private ViewPager viewPager;
    private int dotCount;
    private ImageView[] dots;
    private boolean isViewing;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserHomeBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this,providerFactory).get(UserHomeFragmentViewModel.class);
        viewPager = binding.homeContentSliderViewpager;
        navController = Navigation.findNavController(view);

        subscribeDataSource();
    }

    private void subscribeDataSource() {
        viewModel.getListOfArticle().removeObservers(getViewLifecycleOwner());
        viewModel.getListOfArticle().observe(getViewLifecycleOwner(), new Observer<Resource<List<ZakatArticle>>>() {
            @Override
            public void onChanged(Resource<List<ZakatArticle>> listResource) {
                switch (listResource.status){
                    case LOADING:
                        Log.d(TAG, "onChanged: Loading");
                    case SUCCESS:
                        Log.d(TAG, "onChanged: have"+listResource.data.size());
                        setUpRecyclerViewArticle(listResource.data);
                    case ERROR:
                        Log.d(TAG, "onChanged: fail to getData");
                }
            }
        });

        viewModel.getSliderList().removeObservers(getViewLifecycleOwner());
        viewModel.getSliderList().observe(getViewLifecycleOwner(), new Observer<Resource<List<Featured>>>() {
            @Override
            public void onChanged(Resource<List<Featured>> listResource) {
                switch (listResource.status){
                    case LOADING:
                        Log.d(TAG, "onChanged: Loading data");
                    case SUCCESS:
                        Log.d(TAG, "onChanged: "+listResource.data.size());
                        settingUp_Dot(getContext(), (ArrayList<Featured>) listResource.data,viewPager,binding.SliderDots);
                    case ERROR:
                        Log.d(TAG, "onChanged: Error");
                }
            }
        });

    }

    private void setUpRecyclerViewArticle(List<ZakatArticle> articleList){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), (ArrayList<ZakatArticle>) articleList,this::onItemClick);
        binding.userHomeArticleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.userHomeArticleRecyclerView.setAdapter(adapter);
    }


    private void settingUp_Dot(final Context context, ArrayList<Featured> SliderImage, ViewPager viewPager, LinearLayout sliderDotspanel) {
        ImageSliderAdapter adapterImage = new ImageSliderAdapter(context,SliderImage);
        viewPager.setAdapter(adapterImage);
        //  Log.i("Array","Array"+SliderImage.size());
        dotCount = SliderImage.size();
        dots = new ImageView[dotCount];

        for (int i = 0; i < dotCount; i++) {

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.inactive_indecator));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_indecator));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.inactive_indecator));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_indecator));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        isViewing = true;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AutoSliderImageTask(), 3000, 5000);

        Log.d("AutoSliderImageTask", "settingUp_Dot: "+isVisible());

    }

    @Override
    public void onItemClick(ZakatArticle article ,int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARTICLE_KEY,article);
        navController.navigate(R.id.action_article_to_details_view,bundle);

    }

    public class AutoSliderImageTask extends TimerTask {
        int pageCount =0;
        int nextPage =0;
        private static final String TAG = "AutoSliderImageTask";

        @Override
        public void run() {
            Log.d(TAG, "run: is visible p count"+pageCount+" Next page count "+nextPage);
            if(isVisible()) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            pageCount = viewPager.getAdapter().getCount();

                            if (viewPager.getCurrentItem() == nextPage) {
                                int setState = (nextPage + 1);
                                viewPager.setCurrentItem(setState);
                                nextPage++;
                            } else {
                                viewPager.setCurrentItem(0);
                                nextPage = 0;
                            }


//                        if (viewPager.getCurrentItem() == 0) {
//                            viewPager.setCurrentItem(1);
//                            Log.i("MySlide",""+viewPager.getAdapter().getCount());
//
//                        } else if (viewPager.getCurrentItem() == 1) {
//                            viewPager.setCurrentItem(2);
//                            Log.i("MySlide",""+viewPager.getAdapter().getCount());
//                        } else {
//                            viewPager.setCurrentItem(0);
//                            Log.i("MySlide",""+viewPager.getAdapter().getCount());
//                        }
                        }

                });

            }
        }
    }

}

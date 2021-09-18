package com.example.zakat.views.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.zakat.R;
import com.example.zakat.databinding.UserHomeSliderSingleItemBinding;
import com.example.zakat.models.Featured;

import java.util.ArrayList;

public class ImageSliderAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Featured> data;

    private LayoutInflater inflater;


    public ImageSliderAdapter(Context context, ArrayList<Featured> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        UserHomeSliderSingleItemBinding binding;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        binding = UserHomeSliderSingleItemBinding.inflate(inflater,container,false);

        //image.setImageResource(Image.get(position));

        if (data.get(position).getImage().contentEquals("None")){
            binding.homeScreenSliderImage.setImageResource(R.drawable.zakat_picture_for_seeing);
            binding.homeScreenSliderTitle.setText(data.get(position).getTitle());
        }

        else {
            Glide.with(context).load(data.get(position).getImage()).into(binding.homeScreenSliderImage);
            binding.homeScreenSliderTitle.setText(data.get(position).getTitle());
        }



        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}

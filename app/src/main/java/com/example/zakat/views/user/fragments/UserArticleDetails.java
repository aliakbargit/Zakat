package com.example.zakat.views.user.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.zakat.R;
import com.example.zakat.databinding.UserArticleDetailsBinding;
import com.example.zakat.models.core.ZakatArticle;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.example.zakat.util.Constrains.ARTICLE_KEY;

public class UserArticleDetails extends DaggerFragment {
    private static final String TAG = "UserArticleDetails";
    private UserArticleDetailsBinding binding;

    @Inject
    RequestManager requestManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserArticleDetailsBinding.inflate(inflater,container,false);

        Bundle args = getArguments();
        if(args != null){
            ZakatArticle article = (ZakatArticle)args.getParcelable(ARTICLE_KEY);
            binding.setArticle(article);
            requestManager.load(article.getImage()).into(binding.articleImage);
        }
        return binding.getRoot();
      //  return inflater.inflate(R.layout.user_article_details,container,false);
    }

}

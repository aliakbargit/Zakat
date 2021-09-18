package com.example.zakat.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.zakat.util.Constrains.SuccessOrFailure;


public class SuccessOrErrorResource<T> {
    @NonNull
    public final SuccessOrFailure status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public SuccessOrErrorResource(@NonNull SuccessOrFailure status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T>SuccessOrErrorResource<T> success(@Nullable T data){
        return new SuccessOrErrorResource<>(SuccessOrFailure.SUCCESS,data,null);
    }

    public static <T>SuccessOrErrorResource<T> failure(@Nullable String msg,@Nullable T data){
        return new SuccessOrErrorResource<>(SuccessOrFailure.FAILURE,data,msg);
    }

}

package com.example.pttmobile4.api;

import com.example.pttmobile4.models.User;
import com.example.pttmobile4.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("users")
    Call<User> createUser(@Body User user);

}

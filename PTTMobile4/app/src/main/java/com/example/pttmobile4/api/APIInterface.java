package com.example.pttmobile4.api;

import com.example.pttmobile4.models.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("users")
    @Headers("Content-Type: application/json")
    Call<User> createUser(@Body RequestBody body);

}

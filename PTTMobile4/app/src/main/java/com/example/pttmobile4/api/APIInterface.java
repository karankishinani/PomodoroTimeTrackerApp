package com.example.pttmobile4.api;

import com.example.pttmobile4.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("users")
    Call<UserResponse> createUser(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("email") String email
    );

}

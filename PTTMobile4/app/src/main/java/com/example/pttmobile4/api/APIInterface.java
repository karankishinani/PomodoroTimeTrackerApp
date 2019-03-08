package com.example.pttmobile4.api;

import com.example.pttmobile4.models.Project;
import com.example.pttmobile4.models.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @POST("users")
    @Headers("Content-Type: application/json")
    Call<User> createUser(@Body RequestBody body);

    @POST("users/{id}/projects")
    @Headers("Content-Type: application/json")
    Call<Project> createProject(@Path("id") int id, @Body RequestBody body);

}
